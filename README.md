# 🍽️ RecipeNest – Modern Android Recipe App

RecipeNest is a modern Android Recipe Application built using **Kotlin** and **Jetpack Compose** in Android Studio as part of the **ApexPlanet Android App Development Internship – Task 3**.

The app provides a beautiful and smooth user experience with real-time recipe data, modern UI design, API integration, category filtering, persistent favorites, dark mode support, and professional animations.

---

# ✨ Features

## 🚀 Core Features

- Splash Screen
- Modern Jetpack Compose UI
- Bottom Navigation
- Real-Time Recipe API Integration
- Internet Recipe Images
- Search Functionality
- Recipe Detail Screen
- Favorites Screen
- Profile Screen
- Dark Mode Support
- Responsive Layout

---

## 🌐 API & Backend Features

- Retrofit API Integration
- JSON Parsing using Gson
- Real Recipe Fetching
- Dynamic Recipe Loading
- Pull-to-Refresh Support
- Error Handling UI
- Empty State UI
- Smooth Data Updates

---

## 🎨 UI/UX Features

- Featured Recipe Banner
- Category Filtering
- Shimmer Loading Animation
- Smooth Screen Transitions
- Modern Material 3 Design
- Interactive Favorite Button
- Beautiful Card Layouts
- Professional Typography & Spacing

---

## ❤️ Advanced Features

- Persistent Favorites using Room Database
- Offline Favorite Storage
- Real-Time Favorite Updates
- Animated Navigation Transitions
- Search + Category Combined Filtering

---

# 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Kotlin | Programming Language |
| Jetpack Compose | Modern Android UI |
| Retrofit | API Integration |
| Gson Converter | JSON Parsing |
| Coil | Image Loading |
| Room Database | Local Storage |
| Material 3 | UI Components |
| Coroutines | Asynchronous Programming |

---

# 🌐 API Used

### TheMealDB API

https://www.themealdb.com/api.php

Used for:
- Fetching recipes
- Recipe images
- Recipe categories
- Recipe instructions

---

# 📱 App Screenshots

---

## 🏠 Home Screen

![Home Screen](screenshots/home.png)

---

## 🍱 Online Recipes

![Online Recipes](screenshots/onlinerecipes.png)

---

## 🧩 Category Filtering

![Category Filtering](screenshots/categories.png)

---

## 🔍 Search Functionality

![Search Functionality](screenshots/search.png)

---

## 📖 Recipe Detail Screen

![Recipe Detail Screen](screenshots/description2.png)

---

## ❤️ Favorites Screen

![Favorites Screen](screenshots/favourites2.png)

---

## 🌙 Dark Mode

![Dark Mode](screenshots/darkmode.png)

---

## ⚠️ Empty State UI

![Empty State](screenshots/wrongfood.png)

---

# 📂 Project Structure

```text
com.example.recipenest
│
├── api
│   ├── RecipeApiService.kt
│   ├── FavoriteRecipeDao.kt
│   └── RecipeDatabase.kt
│
├── components
│   ├── CategoryChip.kt
│   ├── FeaturedBanner.kt
│   ├── OnlineRecipeCard.kt
│   ├── ShimmerRecipeCard.kt
│   └── ThemeManager.kt
│
├── model
│   ├── OnlineRecipe.kt
│   └── FavoriteRecipeEntity.kt
│
├── screens
│   ├── HomeScreen.kt
│   ├── FavoriteScreen.kt
│   ├── ProfileScreen.kt
│   └── OnlineRecipeDetailScreen.kt
│
└── navigation