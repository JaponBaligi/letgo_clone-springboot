# Takı Kazan Clone - Spring Boot Application

This is a complete clone of the Letgo homepage built with Java Spring Boot, featuring a modern, responsive design that closely matches the original Letgo website, rebranded as "Takı Kazan".

## Features

- **Responsive Design**: Mobile-first approach with Bootstrap 5
- **Search Functionality**: Full-text search across products
- **Product Management**: CRUD operations for products
- **Category Filtering**: Filter products by category
- **Modern UI**: Clean, modern interface matching Letgo's design, rebranded as Takı Kazan
- **Database Integration**: H2 in-memory database for development
- **Security**: Spring Security configuration for public access

## Technology Stack

- **Backend**: Java 21, Spring Boot 3.5.6
- **Frontend**: Thymeleaf, Bootstrap 5, JavaScript
- **Database**: H2 (in-memory)
- **Build Tool**: Gradle
- **Security**: Spring Security

## Project Structure

```
src/
├── main/
│   ├── java/com/japonbaligi/letgoclone/
│   │   ├── config/
│   │   │   └── SecurityConfig.java
│   │   ├── controller/
│   │   │   └── HomeController.java
│   │   ├── entity/
│   │   │   └── Product.java
│   │   ├── repository/
│   │   │   └── ProductRepository.java
│   │   ├── service/
│   │   │   └── ProductService.java
│   │   └── LetgocloneApplication.java
│   └── resources/
│       ├── static/
│       │   ├── css/
│       │   │   └── style.css
│       │   └── js/
│       │       └── script.js
│       ├── templates/
│       │   ├── layout.html
│       │   ├── index.html
│       │   └── search.html
│       └── application.properties
```

## Getting Started

### Prerequisites

- Java 21 or higher
- Gradle (or use the included wrapper)

### Running the Application

1. **Clone the repository** (if not already done):
   ```bash
   git clone <repository-url>
   cd letgoclone
   ```

2. **Run the application**:
   ```bash
   ./gradlew bootRun
   ```
   
   Or on Windows:
   ```cmd
   gradlew.bat bootRun
   ```

3. **Access the application**:
   - Open your browser and go to: `http://localhost:8080`
   - The homepage will load with sample products

### Database Console

- H2 Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:takikazanclone`
- Username: `sa`
- Password: `password`

## Features Overview

### Homepage (`/`)
- Hero section with call-to-action
- Search functionality
- Popular categories grid
- Featured products showcase
- How it works section
- Statistics section
- Download app section

### Search Page (`/search`)
- Full-text search across products
- Category filtering
- Location filtering
- Price range filtering
- Sort options
- Pagination (ready for implementation)

### Sample Data
The application automatically initializes with sample products including:
- iPhone 13 Pro Max
- MacBook Air M2
- BMW X5
- PlayStation 5
- Samsung Galaxy S23 Ultra
- AirPods Pro
- Nike Air Max
- IKEA Furniture

## Customization

### Adding New Products
Products are automatically created through the `ProductService.initializeSampleData()` method. You can modify this method to add more sample data.

### Styling
- Main styles: `src/main/resources/static/css/style.css`
- Uses CSS custom properties for consistent theming
- Responsive design with mobile-first approach

### JavaScript Features
- Search suggestions
- Smooth animations
- Mobile menu handling
- Back to top button
- Product interactions

## API Endpoints

- `GET /` - Homepage
- `GET /search?q={query}` - Search products
- `GET /h2-console` - Database console

## Development Notes

- The application uses H2 in-memory database for development
- Sample data is automatically loaded on startup
- Spring Security is configured to allow public access to main pages
- Thymeleaf is used for server-side rendering
- Bootstrap 5 provides responsive design
- Font Awesome icons are included

## Future Enhancements

- User authentication and registration
- Product upload functionality
- Real image upload and storage
- Advanced filtering options
- Pagination implementation
- User profiles and favorites
- Messaging system
- Payment integration

## Browser Support

- Chrome (recommended)
- Firefox
- Safari
- Edge

## License

This project is for educational purposes and is a clone of the Letgo website, rebranded as "Takı Kazan".
