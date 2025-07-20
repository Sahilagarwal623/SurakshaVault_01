// ai-commands.js
// List of AI chat trigger phrases and their corresponding operations for SurakshaVault dashboard

// Each entry: { trigger: string|RegExp, response: string, action: function }
// You can add more triggers and actions as needed.

const AI_COMMANDS = [
  {
    trigger: /change theme/i,
    response: 'Toggling theme...',
    action: () => { if (typeof toggleTheme === 'function') toggleTheme(); }
  },
  {
    trigger: /toggle theme/i,
    response: 'Toggling theme...',
    action: () => { if (typeof toggleTheme === 'function') toggleTheme(); }
  },
  {
    trigger: /switch theme/i,
    response: 'Toggling theme...',
    action: () => { if (typeof toggleTheme === 'function') toggleTheme(); }
  },
  {
    trigger: /theme change/i,
    response: 'Toggling theme...',
    action: () => { if (typeof toggleTheme === 'function') toggleTheme(); }
  },
  {
    trigger: /chang theme/i,  // common typo
    response: 'Toggling theme...',
    action: () => { if (typeof toggleTheme === 'function') toggleTheme(); }
  },

  {
    trigger: /show me profile|go to profile|show me my profile/i,
    response: 'Redirecting to your profile...',
    action: () => { window.location.href = 'profile.html'; }
  },
  {
    trigger: /open profile|view profile/i,
    response: 'Redirecting to your profile...',
    action: () => { window.location.href = 'profile.html'; }
  },
  {
    trigger: /go profile/i,  // shorthand
    response: 'Redirecting to your profile...',
    action: () => { window.location.href = 'profile.html'; }
  },
  {
    trigger: /show profile/i,  // shorthand
    response: 'Redirecting to your profile...',
    action: () => { window.location.href = 'profile.html'; }
  },
  {
    trigger: /profile page/i,
    response: 'Redirecting to your profile...',
    action: () => { window.location.href = 'profile.html'; }
  },

  {
    trigger: /show active consents/i,
    response: null, // handled by handleAiControl
    action: () => { if (typeof handleAiControl === 'function') handleAiControl('show-consents'); }
  },
  {
    trigger: /list consents/i,
    response: null,
    action: () => { if (typeof handleAiControl === 'function') handleAiControl('show-consents'); }
  },
  {
    trigger: /display consents/i,
    response: null,
    action: () => { if (typeof handleAiControl === 'function') handleAiControl('show-consents'); }
  },
  {
    trigger: /active consent list/i,
    response: null,
    action: () => { if (typeof handleAiControl === 'function') handleAiControl('show-consents'); }
  },
  {
    trigger: /active concent list/i,
    response: null,
    action: () => { if (typeof handleAiControl === 'function') handleAiControl('show-consents'); }
  },

  {
    trigger: /refresh profile/i,
    response: 'Refreshing profile...',
    action: () => { window.location.href = 'profile.html'; }
  },
  {
    trigger: /reload profile/i,
    response: 'Refreshing profile...',
    action: () => { window.location.href = 'profile.html'; }
  },
  {
    trigger: /refresh my profile/i,
    response: 'Refreshing profile...',
    action: () => { window.location.href = 'profile.html'; }
  },
  {
    trigger: /update profile/i,
    response: 'Refreshing profile...',
    action: () => { window.location.href = 'profile.html'; }
  },

  {
    trigger: /open settings|show settings/i,
    response: 'Opening settings...',
    action: () => {
      const btn = document.getElementById('settingsBtn');
      if (btn) btn.click();
    }
  },
  {
    trigger: /display config|go to preferences/i,
    response: 'Opening settings...',
    action: () => {
      const btn = document.getElementById('settingsBtn');
      if (btn) btn.click();
    }
  },
  {
    trigger: /open configuration/i,
    response: 'Opening settings...',
    action: () => {
      const btn = document.getElementById('settingsBtn');
      if (btn) btn.click();
    }
  },
  {
    trigger: /preferences page/i,
    response: 'Opening settings...',
    action: () => {
      const btn = document.getElementById('settingsBtn');
      if (btn) btn.click();
    }
  },

  {
    trigger: /help|show help|help center/i,
    response: 'Opening help center...',
    action: () => { window.location.href = '#help-center'; }
  },
  {
    trigger: /support|assist me|need help/i,
    response: 'Opening help center...',
    action: () => { window.location.href = '#help-center'; }
  },

  {
    trigger: /logout|log out|sign out/i,
    response: 'Logging out...',
    action: () => { window.location.href = 'auth.html'; }
  },
  {
    trigger: /exit account|logoff|signoff/i,
    response: 'Logging out...',
    action: () => { window.location.href = 'auth.html'; }
  },

  {
    trigger: /toggle sidebar/i,
    response: 'Toggling sidebar...',
    action: () => { if (typeof toggleSidebar === 'function') toggleSidebar(); }
  },
  {
    trigger: /open sidebar/i,
    response: 'Toggling sidebar...',
    action: () => { if (typeof toggleSidebar === 'function') toggleSidebar(); }
  },
  {
    trigger: /close sidebar/i,
    response: 'Toggling sidebar...',
    action: () => { if (typeof toggleSidebar === 'function') toggleSidebar(); }
  },
  {
    trigger: /show side/i,
    response: 'Toggling sidebar...',
    action: () => { if (typeof toggleSidebar === 'function') toggleSidebar(); }
  },
  {
    trigger: /hide sidebar/i,
    response: 'Toggling sidebar...',
    action: () => { if (typeof toggleSidebar === 'function') toggleSidebar(); }
  },

  {
    trigger: /show notifications|open notifications/i,
    response: 'Showing notifications...',
    action: () => {
      const btn = document.getElementById('notificationBtn');
      if (btn) btn.click();
    }
  },
  {
    trigger: /view alerts/i,
    response: 'Showing notifications...',
    action: () => {
      const btn = document.getElementById('notificationBtn');
      if (btn) btn.click();
    }
  },
  {
    trigger: /show alerts/i,
    response: 'Showing notifications...',
    action: () => {
      const btn = document.getElementById('notificationBtn');
      if (btn) btn.click();
    }
  },

  {
    trigger: /show dashboard|go to dashboard/i,
    response: 'Redirecting to dashboard...',
    action: () => { window.location.href = 'dashboard.html'; }
  },
  {
    trigger: /open dashboard/i,
    response: 'Redirecting to dashboard...',
    action: () => { window.location.href = 'dashboard.html'; }
  },
  {
    trigger: /display dashboard/i,
    response: 'Redirecting to dashboard...',
    action: () => { window.location.href = 'dashboard.html'; }
  },
  {
    trigger: /view dashboard page/i,
    response: 'Redirecting to dashboard...',
    action: () => { window.location.href = 'dashboard.html'; }
  },

  {
    trigger: /revert sidebar theme/i,
    response: 'Reverting sidebar theme to default...',
    action: () => {
      if (typeof app !== 'undefined') {
        app.theme = 'light';
        if (typeof updateThemeIcon === 'function') updateThemeIcon();
        document.documentElement.setAttribute('data-theme', 'light');
        localStorage.setItem('sv_theme', 'light');
      }
    }
  },
  {
    trigger: /reset sidebar theme/i,
    response: 'Reverting sidebar theme to default...',
    action: () => {
      if (typeof app !== 'undefined') {
        app.theme = 'light';
        if (typeof updateThemeIcon === 'function') updateThemeIcon();
        document.documentElement.setAttribute('data-theme', 'light');
        localStorage.setItem('sv_theme', 'light');
      }
    }
  },
  {
    trigger: /restore sidebar theme/i,
    response: 'Reverting sidebar theme to default...',
    action: () => {
      if (typeof app !== 'undefined') {
        app.theme = 'light';
        if (typeof updateThemeIcon === 'function') updateThemeIcon();
        document.documentElement.setAttribute('data-theme', 'light');
        localStorage.setItem('sv_theme', 'light');
      }
    }
  },
  {
    trigger: /default sidebar theme/i,
    response: 'Reverting sidebar theme to default...',
    action: () => {
      if (typeof app !== 'undefined') {
        app.theme = 'light';
        if (typeof updateThemeIcon === 'function') updateThemeIcon();
        document.documentElement.setAttribute('data-theme', 'light');
        localStorage.setItem('sv_theme', 'light');
      }
    }
  }
  // Add more commands as needed
];

// Utility to check if a message matches a command
function matchAiCommand(msg) {
  for (const cmd of AI_COMMANDS) {
    if (typeof cmd.trigger === 'string' && msg.toLowerCase().includes(cmd.trigger.toLowerCase())) {
      return cmd;
    } else if (cmd.trigger instanceof RegExp && cmd.trigger.test(msg)) {
      return cmd;
    }
  }
  return null;
}


// No export: this file is meant to be included via <script> in the browser.
// AI_COMMANDS and matchAiCommand are available globally.
