class SurakshaVaultAuth {
  constructor() {
    this.baseUrl = "http://192.168.137.1:8080/auth"
    this.userType = ""
    this.currentScreen = "selection"
    this.jwtToken = ""
    this.currentUsername = ""
    // this.init()
     setTimeout(() => {
    const loader = document.getElementById('initialLoader');
    if (loader) {
      loader.style.opacity = '0';
      setTimeout(() => {
        loader.remove();
        this.init();
      }, 500);
    }
  }, 3000);
    
  }

  init() {
    // Check for existing auth in localStorage
    const savedToken = localStorage.getItem('sv_token');
    const savedUsername = localStorage.getItem('sv_username');
    
    if (savedToken && savedUsername) {
        this.jwtToken = savedToken;
        this.currentUsername = savedUsername;
        document.getElementById("tokenValue").textContent = savedToken;
        this.showScreen("token");
        return; // Skip normal initialization
    }
    
    this.bindEvents()
    this.showScreen("selection")
    this.initializeAnimations()
  }

  initializeAnimations() {
    const roleButtons = document.querySelectorAll(".role-btn")
    roleButtons.forEach((btn, index) => {
      btn.style.animationDelay = `${index * 0.1}s`
      btn.classList.add("animate-in")
    })
  }

  bindEvents() {
    // Navigation events
    document.getElementById("userBtn").addEventListener("click", () => this.initRegistration("user"))
    document.getElementById("partnerBtn").addEventListener("click", () => this.initRegistration("partner"))
    document.getElementById("backToSelection").addEventListener("click", () => this.showScreen("selection"))
    document.getElementById("backToSelectionFromLogin").addEventListener("click", () => this.showScreen("selection"))
    document.getElementById("toLoginBtn").addEventListener("click", () => this.showScreen("login"))
    document
      .getElementById("toRegisterBtn")
      .addEventListener("click", () => this.initRegistration(this.userType || "user"))
    document.getElementById("backToLogin").addEventListener("click", () => this.showScreen("login"))
    document.getElementById("backToLoginLink").addEventListener("click", () => this.showScreen("login"))
    document.getElementById("forgotPasswordBtn").addEventListener("click", () => this.showScreen("forgotPassword"))
    document.getElementById("logoutBtn").addEventListener("click", () => this.logout())
    
    // New events for user info form
    document.getElementById("userInfoFormFields").addEventListener("submit", (e) => this.handleUserInfo(e))
    document.getElementById("backToLoginFromUserInfo").addEventListener("click", () => this.showScreen("login"))

    // Form events
    document.getElementById("regForm").addEventListener("submit", (e) => this.handleRegistration(e))
    document.getElementById("loginForm").addEventListener("submit", (e) => this.handleLogin(e))
    document.getElementById("forgotPasswordForm").addEventListener("submit", (e) => this.handleForgotPassword(e))

    // Token copy event
    document.getElementById("copyToken").addEventListener("click", () => this.copyToken())

    // Password toggle events
    document.addEventListener("click", (e) => {
      if (e.target.closest(".toggle-password")) {
        this.togglePassword(e.target.closest(".toggle-password"))
      }
    })

    // Add input focus animations
    document.addEventListener("focusin", (e) => {
      if (e.target.matches("input")) {
        this.animateInputFocus(e.target, true)
      }
    })

    document.addEventListener("focusout", (e) => {
      if (e.target.matches("input")) {
        this.animateInputFocus(e.target, false)
      }
    })
  }

  animateInputFocus(input, isFocused) {
    const inputGroup = input.closest(".input-group")
    if (inputGroup) {
      if (isFocused) {
        inputGroup.style.transform = "scale(1.02)"
      } else {
        inputGroup.style.transform = "scale(1)"
      }
    }
  }

  showScreen(screenName) {
    const currentScreen = document.querySelector(".screen.active")
    const newScreen = document.getElementById(screenName)

    if (currentScreen) {
      currentScreen.style.transform = "translateX(-30px)"
      currentScreen.style.opacity = "0"

      setTimeout(() => {
        currentScreen.classList.remove("active")
        newScreen.classList.add("active")
        newScreen.style.transform = "translateX(30px)"
        newScreen.style.opacity = "0"

        setTimeout(() => {
          newScreen.style.transform = "translateX(0)"
          newScreen.style.opacity = "1"
        }, 50)
      }, 200)
    } else {
      newScreen.classList.add("active")
    }

    this.currentScreen = screenName
  }

  showLoading(show = true) {
    const overlay = document.getElementById("loadingOverlay")
    const submitBtns = document.querySelectorAll(".submit-btn")

    if (show) {
      overlay.classList.add("active")
      submitBtns.forEach((btn) => btn.classList.add("loading"))
    } else {
      overlay.classList.remove("active")
      submitBtns.forEach((btn) => btn.classList.remove("loading"))
    }
  }

  showAlert(message, type = "error") {
    const container = document.getElementById("alertContainer")
    const alert = document.createElement("div")
    alert.className = `alert ${type}`

    const iconMap = {
      success: "check-circle",
      info: "info-circle",
      error: "exclamation-triangle",
      warning: "exclamation-triangle",
    }

    const icon = iconMap[type] || iconMap.error

    alert.innerHTML = `
      <i class="fas fa-${icon}"></i>
      <span>${message}</span>
    `

    container.appendChild(alert)

    // Add entrance animation
    setTimeout(() => {
      alert.style.transform = "translateX(0)"
    }, 10)

    // Auto remove after 5 seconds
    setTimeout(() => {
      alert.style.transform = "translateX(100%)"
      alert.style.opacity = "0"
      setTimeout(() => {
        if (alert.parentNode) {
          alert.remove()
        }
      }, 300)
    }, 5000)
  }

  initRegistration(type) {
    this.userType = type
    this.showScreen("registration")

    const regForm = document.getElementById("regForm")
    const regTitle = document.getElementById("regTitle")

    regForm.innerHTML = ""

    if (type === "user") {
      regTitle.textContent = "Create User Account"
      this.addField("username", "text", "Username", "user")
      this.addField("fullName", "text", "Full Name", "id-card")
      this.addField("phoneNumber", "tel", "Phone Number", "phone")
      this.addField("email", "email", "Email Address", "envelope")
      this.addField("password", "password", "Password", "lock")
      this.addField("confirmPassword", "password", "Confirm Password", "lock")
      this.addField("age", "number", "Age", "calendar")
    } else {
      regTitle.textContent = "Create Partner Account"
      this.addField("username", "text", "Company Username", "building")
      this.addField("email", "email", "Business Email", "envelope")
      this.addField("companyAge", "number", "Company Age (Years)", "calendar")
      this.addField("password", "password", "Password", "lock")
      this.addField("confirmPassword", "password", "Confirm Password", "lock")
    }

    const submitBtn = document.createElement("button")
    submitBtn.innerHTML = `
      <span class="btn-text">
        <i class="fas fa-user-plus"></i> 
        Create Secure Account
      </span>
      <div class="btn-loader">
        <div class="spinner"></div>
      </div>
    `
    submitBtn.type = "submit"
    submitBtn.className = "submit-btn"
    regForm.appendChild(submitBtn)

    // Add stagger animation to form fields
    const inputGroups = regForm.querySelectorAll(".input-group")
    inputGroups.forEach((group, index) => {
      group.style.opacity = "0"
      group.style.transform = "translateY(20px)"
      setTimeout(() => {
        group.style.transition = "all 0.4s ease"
        group.style.opacity = "1"
        group.style.transform = "translateY(0)"
      }, index * 100)
    })
  }

  addField(name, type, placeholder, icon) {
    const inputGroup = document.createElement("div")
    inputGroup.className = "input-group"

    const iconElement = document.createElement("div")
    iconElement.className = "input-icon"
    iconElement.innerHTML = `<i class="fas fa-${icon}"></i>`

    const input = document.createElement("input")
    input.type = type
    input.id = name
    input.name = name
    input.placeholder = placeholder
    input.required = true

    inputGroup.appendChild(iconElement)
    inputGroup.appendChild(input)

    if (type === "password") {
      const toggleBtn = document.createElement("button")
      toggleBtn.type = "button"
      toggleBtn.className = "toggle-password"
      toggleBtn.innerHTML = '<i class="fas fa-eye"></i>'
      inputGroup.appendChild(toggleBtn)

      if (name === "password") {
        input.addEventListener("input", () => this.checkPasswordStrength(input.value))
      }
    }

    document.getElementById("regForm").appendChild(inputGroup)

    if (name === "password") {
      const strengthIndicator = document.createElement("div")
      strengthIndicator.className = "password-strength"
      strengthIndicator.innerHTML = `
        <div class="strength-text">Password Strength: <span id="strengthText">Enter password</span></div>
        <div class="strength-bar">
          <div id="strengthFill" class="strength-fill"></div>
        </div>
      `
      document.getElementById("regForm").appendChild(strengthIndicator)
    }
  }

  checkPasswordStrength(password) {
    const strengthText = document.getElementById("strengthText")
    const strengthFill = document.getElementById("strengthFill")

    if (!strengthText || !strengthFill) return

    let strength = 0
    let text = "Weak"
    let className = "strength-weak"

    if (password.length >= 8) strength++
    if (/[a-z]/.test(password)) strength++
    if (/[A-Z]/.test(password)) strength++
    if (/[0-9]/.test(password)) strength++
    if (/[^A-Za-z0-9]/.test(password)) strength++

    const strengthLevels = [
      { text: "Very Weak", className: "strength-weak" },
      { text: "Weak", className: "strength-weak" },
      { text: "Fair", className: "strength-fair" },
      { text: "Good", className: "strength-good" },
      { text: "Strong", className: "strength-strong" },
    ]

    const level = strengthLevels[Math.min(strength, 4)]
    text = level.text
    className = level.className

    strengthText.textContent = text
    strengthFill.className = `strength-fill ${className}`
  }

  togglePassword(button) {
    const input = button.parentElement.querySelector("input")
    const icon = button.querySelector("i")

    if (input.type === "password") {
      input.type = "text"
      icon.className = "fas fa-eye-slash"
    } else {
      input.type = "password"
      icon.className = "fas fa-eye"
    }

    // Add toggle animation
    button.style.transform = "scale(0.9)"
    setTimeout(() => {
      button.style.transform = "scale(1)"
    }, 150)
  }

  validateForm(formData, isRegistration = false) {
    if (isRegistration) {
      if (formData.password !== formData.confirmPassword) {
        this.showAlert("Passwords do not match!")
        return false
      }

      if (formData.password.length < 8) {
        this.showAlert("Password must be at least 8 characters long!")
        return false
      }

      if (formData.email && !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
        this.showAlert("Please enter a valid email address!")
        return false
      }
    }

    return true
  }

  async handleRegistration(e) {
    e.preventDefault()

    const formData = new FormData(e.target)
    const data = {}

    formData.forEach((value, key) => {
      if (key === "age" || key === "companyAge") {
        data[key] = Number(value)
      } else if (key !== "confirmPassword") {
        data[key] = value
      }
    })

    data.confirmPassword = formData.get("confirmPassword")

    if (!this.validateForm(data, true)) return

    delete data.confirmPassword

    const endpoint = this.userType === "user" ? "/userRegistration" : "/PartnerRegistration"

    this.showLoading(true)

    try {
      const response = await fetch(this.baseUrl + endpoint, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
      })

      if (!response.ok) {
        throw new Error("Registration failed. Please try again.")
      }

      // Automatically log in after successful registration
      const loginData = {
        username: data.username,
        password: data.password
      }

      const loginResponse = await fetch(`${this.baseUrl}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(loginData),
      })

      if (!loginResponse.ok) {
        throw new Error("Automatic login failed after registration")
      }

      const token = await loginResponse.text()
      this.jwtToken = token
      this.currentUsername = data.username
      
      // Save to localStorage
      localStorage.setItem('sv_token', token)
      localStorage.setItem('sv_username', data.username)

      // Show profile completion screen for users, token screen for partners
      if (this.userType === "user") {
        this.showScreen("userInfoForm")
        this.showAlert("Account created! Please complete your profile.", "success")
      } else {
        document.getElementById("tokenValue").textContent = token
        this.showScreen("token")
        this.showAlert("Partner account created successfully!", "success")
      }
    } catch (error) {
      this.showAlert(error.message)
    } finally {
      this.showLoading(false)
    }
  }

  async handleLogin(e) {
    e.preventDefault()

    const username = document.getElementById("loginUsername").value
    const password = document.getElementById("loginPassword").value

    this.showLoading(true)

    try {
      // Login request
      const loginResponse = await fetch(`${this.baseUrl}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      })

      if (!loginResponse.ok) {
        throw new Error("Invalid credentials. Please check your username and password.")
      }

      const token = await loginResponse.text()
      this.jwtToken = token
      this.currentUsername = username
      
      // Save to localStorage
      localStorage.setItem('sv_token', token);
      localStorage.setItem('sv_username', username);

      // Check if profile exists
      const profileResponse = await fetch(`http://192.168.137.1:8080/userinfo/${username}`, {
        method: "GET",
        headers: {
          "Authorization": `Bearer ${token}`
        }
      })

      if (profileResponse.ok) {
        // Profile exists - show token screen
        document.getElementById("tokenValue").textContent = token
        this.showScreen("token")
        this.showAlert("Login successful! Welcome back.", "success")
      } else {
        // Profile doesn't exist - show user info form
        this.showScreen("userInfoForm")
        this.showAlert("Login successful! Please complete your profile.", "success")
      }
    } catch (error) {
      this.showAlert(error.message)
    } finally {
      this.showLoading(false)
    }
  }

  async handleUserInfo(e) {
    e.preventDefault()
    
    const adhaarNumber = document.getElementById("adhaarNumber").value
    const panNumber = document.getElementById("panNumber").value
    const annualIncome = document.getElementById("annualIncome").value
    const cibilScore = document.getElementById("cibilScore").value

    const data = {
      username: this.currentUsername,
      adhaarNumber,
      panNumber,
      annualIncome: Number(annualIncome),
      cibilScore: Number(cibilScore),
      transactions: []
    }

    this.showLoading(true)

    try {
      const response = await fetch(`http://192.168.137.1:8080/userinfo/${this.currentUsername}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${this.jwtToken}`
        },
        body: JSON.stringify(data)
      })

      if (!response.ok) {
        throw new Error("Failed to save profile")
      }

      // After saving, go to token screen
      document.getElementById("tokenValue").textContent = this.jwtToken
      
      // Save to localStorage again (redundant but safe)
      localStorage.setItem('sv_token', this.jwtToken);
      localStorage.setItem('sv_username', this.currentUsername);
      
      this.showScreen("token")
      this.showAlert("Profile saved successfully!", "success")
    } catch (error) {
      this.showAlert(error.message)
    } finally {
      this.showLoading(false)
    }
  }

  async handleForgotPassword(e) {
    e.preventDefault()

    const identifier = document.getElementById("recoveryIdentifier").value

    this.showLoading(true)

    try {
      await new Promise((resolve) => setTimeout(resolve, 2000))

      this.showAlert("Password recovery link sent to your email!", "success")
      setTimeout(() => this.showScreen("login"), 3000)
    } catch (error) {
      this.showAlert("Failed to send recovery email. Please try again.")
    } finally {
      this.showLoading(false)
    }
  }

  copyToken() {
    const tokenValue = document.getElementById("tokenValue").textContent
    const copyBtn = document.getElementById("copyToken")

    navigator.clipboard
      .writeText(tokenValue)
      .then(() => {
        this.showAlert("Token copied to clipboard!", "success")

        // Visual feedback
        const originalText = copyBtn.innerHTML
        copyBtn.innerHTML = '<i class="fas fa-check"></i><span>Copied!</span>'
        copyBtn.style.background = "rgba(72, 187, 120, 0.2)"
        copyBtn.style.borderColor = "#48bb78"
        copyBtn.style.color = "#48bb78"

        setTimeout(() => {
          copyBtn.innerHTML = originalText
          copyBtn.style.background = "rgba(0, 212, 255, 0.1)"
          copyBtn.style.borderColor = "var(--primary-color)"
          copyBtn.style.color = "var(--primary-color)"
        }, 2000)
      })
      .catch(() => {
        this.showAlert("Failed to copy token. Please copy manually.")
      })
  }

  logout() {
    // Clear localStorage
    localStorage.removeItem('sv_token');
    localStorage.removeItem('sv_username');
    
    // Clear session data
    this.jwtToken = ""
    this.currentUsername = ""
    
    this.showAlert("Logged out successfully!", "info")

    // Add logout animation
    const tokenScreen = document.getElementById("token")
    tokenScreen.style.transform = "scale(0.95)"
    tokenScreen.style.opacity = "0.5"

    setTimeout(() => {
      this.showScreen("selection")
      document.getElementById("loginForm").reset()
      document.getElementById("tokenValue").textContent = ""

      // Reset token screen styles
      tokenScreen.style.transform = "scale(1)"
      tokenScreen.style.opacity = "1"
    }, 1000)
  }
}

// Initialize the application
document.addEventListener("DOMContentLoaded", () => {
  new SurakshaVaultAuth()
})

// Add CSS animation classes dynamically
const style = document.createElement("style")
style.textContent = `
  .animate-in {
    animation: slideInUp 0.6s ease-out forwards;
    opacity: 0;
    transform: translateY(30px);
  }
  
  @keyframes slideInUp {
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }
`
document.head.appendChild(style)