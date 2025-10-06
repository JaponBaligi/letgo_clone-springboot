# Takı Kazan Clone - Complete E-commerce Platform

A comprehensive clone of the Letgo marketplace built with Spring Boot, featuring a modern Turkish e-commerce platform for buying and selling second-hand items. This project demonstrates full-stack development with Java Spring Boot, Thymeleaf templating, and modern web technologies.

## 🎯 Project Overview

**Takı Kazan** (meaning "Earn Money" in Turkish) is a complete e-commerce platform that replicates the core functionality of Letgo, a popular second-hand marketplace. The application provides a seamless experience for users to browse, search, and purchase second-hand items with features like user authentication, shopping cart, checkout process, and product management.

### Key Highlights
- **Full E-commerce Functionality**: Complete shopping experience from browsing to checkout
- **Modern Architecture**: Clean separation of concerns with MVC pattern
- **Responsive Design**: Mobile-first approach with Bootstrap 5
- **User Authentication**: Secure login/registration system with session management
- **Shopping Cart**: Persistent cart functionality with real-time updates
- **Payment Integration**: Complete checkout process with receipt generation
- **Admin Features**: Product management and user administration

## 🏗️ Architecture & Technology Stack

### Backend Technologies
- **Java 21**: Latest LTS version with modern language features
- **Spring Boot 3.5.6**: Rapid application development framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Database abstraction layer
- **H2 Database**: In-memory database for development and testing
- **Gradle**: Build automation and dependency management

### Frontend Technologies
- **Thymeleaf**: Server-side templating engine
- **Bootstrap 5**: Responsive CSS framework
- **JavaScript ES6+**: Modern client-side functionality
- **Font Awesome**: Icon library
- **CSS3**: Custom styling with CSS variables

### Development Tools
- **Spring Boot DevTools**: Hot reloading and development utilities
- **H2 Console**: Database management interface
- **Gradle Wrapper**: Consistent build environment

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/japonbaligi/letgoclone/
│   │   ├── config/
│   │   │   └── SecurityConfig.java          # Security configuration
│   │   ├── controller/
│   │   │   ├── AuthController.java          # Authentication endpoints
│   │   │   ├── CheckoutController.java      # Checkout and payment
│   │   │   ├── HomeController.java          # Homepage and search
│   │   │   ├── ProductController.java       # Product details
│   │   │   └── SellController.java          # Product listing
│   │   ├── entity/
│   │   │   ├── User.java                    # User entity
│   │   │   ├── Product.java                 # Product entity
│   │   │   ├── Cart.java                    # Shopping cart
│   │   │   ├── CartItem.java                # Cart items
│   │   │   └── CheckoutInfo.java            # Payment information
│   │   ├── repository/
│   │   │   ├── UserRepository.java          # User data access
│   │   │   ├── ProductRepository.java       # Product data access
│   │   │   ├── CartRepository.java          # Cart data access
│   │   │   ├── CartItemRepository.java      # Cart item data access
│   │   │   └── CheckoutInfoRepository.java  # Payment data access
│   │   ├── service/
│   │   │   ├── AuthService.java             # Authentication logic
│   │   │   ├── ProductService.java          # Product business logic
│   │   │   ├── CartService.java             # Shopping cart logic
│   │   │   └── CheckoutService.java         # Payment processing
│   │   └── LetgocloneApplication.java       # Main application class
│   └── resources/
│       ├── static/
│       │   ├── css/
│       │   │   └── style.css                # Custom styles
│       │   └── js/
│       │       └── script.js                # Client-side functionality
│       ├── templates/
│       │   ├── layout.html                  # Base template
│       │   ├── index.html                   # Homepage
│       │   ├── search.html                  # Search results
│       │   ├── product-detail.html          # Product details
│       │   ├── cart.html                    # Shopping cart
│       │   ├── checkout.html                # Checkout process
│       │   ├── login.html                   # Login page
│       │   ├── register.html                # Registration
│       │   └── sell.html                    # Product listing
│       └── application.properties           # Configuration
```

## 🚀 Getting Started

### Prerequisites
- **Java 21** or higher
- **Gradle** (or use the included wrapper)
- **Git** for version control

### Installation & Setup

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd letgoclone
   ```

2. **Build the project**:
   ```bash
   ./gradlew build
   ```

3. **Run the application**:
   ```bash
   ./gradlew bootRun
   ```
   
   **Windows users**:
   ```cmd
   gradlew.bat bootRun
   ```

4. **Access the application**:
   - Open your browser and navigate to: `http://localhost:8080`
   - The application will automatically load with sample data

### Database Access
- **H2 Console**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:takikazanclone`
- **Username**: `sa`
- **Password**: `password`

## 🔧 Core Features Explained

### 1. User Authentication System
The application implements a complete authentication system with:
- **User Registration**: New users can create accounts with validation
- **Login/Logout**: Secure session-based authentication
- **Demo Account**: Pre-configured demo user for testing
- **Session Management**: Persistent login state across pages

**Key Components**:
- `AuthController`: Handles login/register endpoints
- `AuthService`: Business logic for user management
- `User` entity: User data model with validation

### 2. Product Management
Comprehensive product system with:
- **Product Catalog**: Browse products by category
- **Search Functionality**: Full-text search across all products
- **Product Details**: Detailed product information with images
- **Category Filtering**: Filter products by category and location
- **Sample Data**: Pre-loaded with realistic product data

**Key Components**:
- `ProductController`: Product-related endpoints
- `ProductService`: Product business logic
- `Product` entity: Product data model

### 3. Shopping Cart System
Full shopping cart functionality including:
- **Add to Cart**: Add products with quantity selection
- **Cart Management**: Update quantities, remove items
- **Persistent Cart**: Cart persists across sessions
- **Real-time Updates**: Dynamic cart updates without page refresh

**Key Components**:
- `CartService`: Cart business logic
- `Cart` and `CartItem` entities: Cart data models
- JavaScript integration for dynamic updates

### 4. Checkout & Payment
Complete checkout process with:
- **Payment Information**: Credit card details collection
- **Address Management**: Shipping address handling
- **Receipt Generation**: Unique receipt IDs for orders
- **Order Processing**: Complete order workflow

**Key Components**:
- `CheckoutController`: Checkout endpoints
- `CheckoutService`: Payment processing logic
- `CheckoutInfo` entity: Payment information storage

### 5. Responsive Design
Modern, mobile-first design featuring:
- **Bootstrap 5**: Responsive grid system
- **Custom CSS**: Brand-specific styling
- **Mobile Optimization**: Touch-friendly interface
- **Progressive Enhancement**: Works on all devices

## 🎨 User Interface Features

### Homepage (`/`)
- **Hero Section**: Compelling call-to-action with gradient background
- **Search Bar**: Prominent search functionality
- **Category Grid**: Visual category navigation
- **Featured Products**: Highlighted product showcase
- **Statistics**: Platform usage statistics
- **How It Works**: Step-by-step user guide

### Search & Filtering (`/search`)
- **Advanced Search**: Full-text search with suggestions
- **Filter Options**: Category, location, price range, condition
- **Sort Options**: Price, date, popularity
- **Pagination**: Efficient product browsing
- **Responsive Grid**: Adaptive product layout

### Product Details (`/product/{id}`)
- **Image Gallery**: High-quality product images
- **Product Information**: Detailed specifications
- **Seller Information**: Contact and location details
- **Related Products**: Similar item suggestions
- **Add to Cart**: Direct purchase functionality

### Shopping Cart (`/cart`)
- **Cart Items**: Detailed product list with quantities
- **Price Calculation**: Real-time total calculation
- **Quantity Management**: Update item quantities
- **Checkout Button**: Proceed to payment

### Checkout Process (`/checkout`)
- **Payment Form**: Secure payment information collection
- **Address Form**: Shipping address management
- **Order Summary**: Final order review
- **Receipt Generation**: Confirmation with unique ID

## 🔐 Security Features

### Authentication & Authorization
- **Session-based Authentication**: Secure user sessions
- **Password Validation**: Strong password requirements
- **CSRF Protection**: Cross-site request forgery prevention
- **Input Validation**: Server-side validation for all inputs

### Data Protection
- **SQL Injection Prevention**: JPA/Hibernate protection
- **XSS Protection**: Thymeleaf auto-escaping
- **Secure Headers**: Security headers configuration

## 📊 Database Schema

### Core Entities
- **Users**: User accounts and profiles
- **Products**: Product catalog with images and details
- **Carts**: Shopping cart management
- **CartItems**: Individual cart items with quantities
- **CheckoutInfo**: Payment and shipping information

### Relationships
- One-to-Many: User → Products, User → Carts
- Many-to-One: CartItem → Product, CartItem → Cart
- One-to-One: User → Cart

## 🛠️ Development Features

### Hot Reloading
- **Spring Boot DevTools**: Automatic application restart
- **Live Reload**: Browser refresh on code changes
- **Configuration Properties**: Externalized configuration

### Database Management
- **H2 Console**: Web-based database interface
- **JPA/Hibernate**: Object-relational mapping
- **Automatic Schema**: Database schema generation

### Testing
- **Unit Tests**: Service layer testing
- **Integration Tests**: Controller testing
- **Test Data**: Sample data for development

## 🚀 Deployment Options

### Development
- **Local Development**: Run with `./gradlew bootRun`
- **H2 Database**: In-memory database for development
- **Hot Reloading**: Automatic restart on changes

### Production
- **JAR Packaging**: `./gradlew bootJar`
- **External Database**: PostgreSQL/MySQL configuration
- **Environment Variables**: Production configuration

## 🔮 Future Enhancements

### Planned Features
- **Real Image Upload**: File upload and storage
- **Advanced Search**: Elasticsearch integration
- **User Profiles**: Detailed user profiles and preferences
- **Messaging System**: In-app communication
- **Payment Gateway**: Real payment processing
- **Mobile App**: React Native mobile application
- **Admin Panel**: Comprehensive admin interface
- **Analytics**: User behavior and sales analytics

### Technical Improvements
- **Microservices**: Service decomposition
- **Caching**: Redis integration
- **CDN**: Content delivery network
- **Monitoring**: Application performance monitoring
- **CI/CD**: Automated deployment pipeline

## 🤝 Contributing

### Development Setup
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

### Code Standards
- Follow Java naming conventions
- Write comprehensive tests
- Document public APIs
- Use meaningful commit messages

## 📄 License

This project is created for educational purposes and demonstrates modern web development practices. It's a clone of the Letgo marketplace, rebranded as "Takı Kazan" for the Turkish market.

## 🆘 Support

For questions or issues:
- Check the documentation
- Review the code comments
- Open an issue on GitHub
- Contact the development team

---

**Built with ❤️ using Spring Boot and modern web technologies**