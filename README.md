# Onboarding Animation App ✨

A modern Android onboarding screen built with **Jetpack Compose**, showcasing animated, interactive cards that expand, collapse, and auto-play beautifully.

---

## 🚀 Features

- ✨ Smooth card expansion & collapse
- 🎞️ Auto-play onboarding sequence with delays
- 🌀 Slide-in animation from bottom
- 🌈 Dynamic background gradients per card
- 🖼️ Images loaded via Coil from URLs
- 📝 Collapsed & expanded state texts
- 🔽 Animated dropdown icon
- 📌 Top toolbar with icon and label
- ✅ Final CTA button shown after animation
- 🩺 Fully MVVM + Hilt + Ktor setup

---

## 🧱 Tech Stack

- **Jetpack Compose** (UI + Animations)
- **Hilt** (Dependency Injection)
- **Ktor** (Network client)
- **Coil** (Image loading)
- **MVVM Architecture**
- **Kotlinx Serialization** (JSON parsing)

---

## 🧪 Setup Instructions

1. Clone this repo:
   ```bash
   git clone https://github.com/your-username/onboarding-animation.git
2. Update the api url in constants (as the api shared got experied in 48hrs)
   ```
   file path: utils/Constants.kt
   Update base url and path for Onboarding as needed.

You can modify:
	•	⏱ Timing values (animation durations) from backend
	•	🎨 Card themes (background/stroke/gradients)
	•	📥 CTA button styling and behavior
	•	🛑 Skip or pause animation logic

---

## Folder Structure
- data/
  - model/           → All data classes
  - api/             → API
  - reposirory/      → All Repositories
- di/                → Hilt modules
- ui/
  - components/      → Reusable Composables (Card, TopBar, etc.)
  - screens/         → OnboardingScreen, LandingScreen
  - theme/           → All theme related files (colors, typogragraphy, etc)
  - viewmodel/       → ViewModels
- utils/             → constants

