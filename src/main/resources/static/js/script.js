function initAnimations() { }
function initSearch() { }
function initCategories() { }
function initProducts() { }
function initMobileMenu() { }
function initBackToTop() { }

document.addEventListener('DOMContentLoaded', function () {
    try {
        initAnimations();
        initSearch();
        initCategories();
        initProducts();
        initMobileMenu();
        initBackToTop();
        console.log('placeholder script loaded');
    } catch (e) {
        console.error('placeholder init error', e);
    }
});

function addToCart(productId) {
    fetch('/cart/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `productId=${productId}&quantity=1`
    })
        .then(response => response.text())
        .then(data => {
            if (data === 'success') {
                showNotification('Ürün sepete eklendi!', 'success');
            } else if (data === 'not_logged_in') {
                showNotification('Sepete eklemek için giriş yapmalısınız!', 'warning');
                const modal = new bootstrap.Modal(document.getElementById('loginModal'));
                modal.show();
            } else {
                showNotification('Ürün sepete eklenirken bir hata oluştu!', 'error');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showNotification('Ürün sepete eklenirken bir hata oluştu!', 'error');
        });
}


function initAnimations() {
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver(function (entries) {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('fade-in');
            }
        });
    }, observerOptions);

    document.querySelectorAll('.category-card, .product-card').forEach(card => {
        observer.observe(card);
    });
}

function initSearch() {
    const searchInput = document.querySelector('.search-input');
    const searchForm = document.querySelector('form[action="/search"]');

    if (searchInput && searchForm) {
        const suggestions = [
            'iPhone 13',
            'MacBook Air',
            'BMW X5',
            'PlayStation 5',
            'Samsung Galaxy',
            'AirPods Pro',
            'Nike Ayakkabı',
            'Adidas Çanta',
            'Laptop',
            'Telefon'
        ];

        let suggestionContainer = null;

        searchInput.addEventListener('input', function () {
            const query = this.value.toLowerCase();

            if (query.length > 1) {
                const filteredSuggestions = suggestions.filter(suggestion =>
                    suggestion.toLowerCase().includes(query)
                );

                showSuggestions(filteredSuggestions);
            } else {
                hideSuggestions();
            }
        });

        searchInput.addEventListener('blur', function () {
            setTimeout(hideSuggestions, 200);
        });

        function showSuggestions(suggestions) {
            hideSuggestions();

            if (suggestions.length === 0) return;

            suggestionContainer = document.createElement('div');
            suggestionContainer.className = 'search-suggestions';
            suggestionContainer.style.cssText = `
                position: absolute;
                top: 100%;
                left: 0;
                right: 0;
                background: white;
                border: 1px solid #e0e0e0;
                border-radius: 0 0 12px 12px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                z-index: 1000;
                max-height: 200px;
                overflow-y: auto;
            `;

            suggestions.forEach(suggestion => {
                const suggestionItem = document.createElement('div');
                suggestionItem.className = 'suggestion-item';
                suggestionItem.style.cssText = `
                    padding: 12px 20px;
                    cursor: pointer;
                    border-bottom: 1px solid #f0f0f0;
                    transition: background-color 0.2s;
                `;
                suggestionItem.textContent = suggestion;

                suggestionItem.addEventListener('mouseenter', function () {
                    this.style.backgroundColor = '#f8f9fa';
                });

                suggestionItem.addEventListener('mouseleave', function () {
                    this.style.backgroundColor = 'white';
                });

                suggestionItem.addEventListener('click', function () {
                    searchInput.value = suggestion;
                    hideSuggestions();
                    searchForm.submit();
                });

                suggestionContainer.appendChild(suggestionItem);
            });

            searchInput.parentElement.style.position = 'relative';
            searchInput.parentElement.appendChild(suggestionContainer);
        }

        function hideSuggestions() {
            if (suggestionContainer) {
                suggestionContainer.remove();
                suggestionContainer = null;
            }
        }
    }
}

function initCategories() {
    document.querySelectorAll('.category-card').forEach(card => {
        card.addEventListener('click', function () {
            const categoryName = this.querySelector('h6').textContent;

            this.style.transform = 'scale(0.95)';
            setTimeout(() => {
                this.style.transform = '';
            }, 150);

            console.log(`Navigating to category: ${categoryName}`);
        });
    });
}

function initProducts() {
    document.querySelectorAll('.product-card').forEach(card => {
        card.addEventListener('click', function () {
            const productTitle = this.querySelector('h6').textContent;

            this.style.transform = 'scale(0.98)';
            setTimeout(() => {
                this.style.transform = '';
            }, 150);

            console.log(`Viewing product: ${productTitle}`);
        });
    });
}

function initMobileMenu() {
    const navbarToggler = document.querySelector('.navbar-toggler');
    const navbarCollapse = document.querySelector('.navbar-collapse');

    if (navbarToggler && navbarCollapse) {
        navbarToggler.addEventListener('click', function () {
            navbarCollapse.classList.toggle('show');
        });

        document.addEventListener('click', function (event) {
            if (!navbarToggler.contains(event.target) && !navbarCollapse.contains(event.target)) {
                navbarCollapse.classList.remove('show');
            }
        });
    }
}

function showLoading(element) {
    const spinner = document.createElement('div');
    spinner.className = 'spinner';
    element.appendChild(spinner);
}

function hideLoading(element) {
    const spinner = element.querySelector('.spinner');
    if (spinner) {
        spinner.remove();
    }
}

document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});

function toggleFavorite(element) {
    const icon = element.querySelector('i');
    if (icon.classList.contains('far')) {
        icon.classList.remove('far');
        icon.classList.add('fas');
        icon.style.color = '#ff6b35';
        showNotification('Favorilere eklendi!');
    } else {
        icon.classList.remove('fas');
        icon.classList.add('far');
        icon.style.color = '#6c757d';
        showNotification('Favorilerden çıkarıldı!');
    }
}

function showNotification(message, type) {
    if (typeof type === 'undefined') {
        type = 'success';
    }
    const notification = document.createElement('div');
    notification.className = `alert alert-${type === 'success' ? 'success' : 'danger'} alert-dismissible fade show position-fixed`;
    notification.style.cssText = 'top: 20px; right: 20px; z-index: 9999; min-width: 300px;';
    notification.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    `;

    document.body.appendChild(notification);

    setTimeout(() => {
        if (notification.parentNode) {
            notification.remove();
        }
    }, 3000);
}

const style = document.createElement('style');
style.textContent = `
    @keyframes slideOutRight {
        from {
            opacity: 1;
            transform: translateX(0);
        }
        to {
            opacity: 0;
            transform: translateX(100%);
        }
    }
`;
document.head.appendChild(style);

function initLazyLoading() {
    const images = document.querySelectorAll('img[data-src]');

    const imageObserver = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                const img = entry.target;
                img.src = img.dataset.src;
                img.classList.remove('lazy');
                imageObserver.unobserve(img);
            }
        });
    });

    images.forEach(img => imageObserver.observe(img));
}

document.addEventListener('DOMContentLoaded', initLazyLoading);

function initBackToTop() {
    const backToTopButton = document.createElement('button');
    backToTopButton.innerHTML = '<i class="fas fa-arrow-up"></i>';
    backToTopButton.style.cssText = `
        position: fixed;
        bottom: 20px;
        right: 20px;
        background: var(--letgo-primary);
        color: white;
        border: none;
        border-radius: 50%;
        width: 50px;
        height: 50px;
        cursor: pointer;
        box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        z-index: 1000;
        opacity: 0;
        transition: all 0.3s ease;
    `;

    document.body.appendChild(backToTopButton);

    window.addEventListener('scroll', () => {
        if (window.pageYOffset > 300) {
            backToTopButton.style.opacity = '1';
        } else {
            backToTopButton.style.opacity = '0';
        }
    });

    backToTopButton.addEventListener('click', () => {
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        });
    });
}

document.addEventListener('DOMContentLoaded', initBackToTop);

function initCheckout() {
    const cardNumberInput = document.getElementById('cardNumber');
    if (cardNumberInput) {
        cardNumberInput.addEventListener('input', function (e) {
            let value = e.target.value.replace(/\s/g, '').replace(/[^0-9]/gi, '');
            let formattedValue = value.match(/.{1,4}/g)?.join(' ') || value;
            e.target.value = formattedValue;
        });
    }
}

function loadSavedCheckoutInfo() {
    const select = document.getElementById('savedCheckoutInfo');
    if (!select) return;

    const checkoutInfoId = select.value;

    if (checkoutInfoId) {
        fetch(`/checkout/info/${checkoutInfoId}`)
            .then(response => response.json())
            .then(data => {
                if (data) {
                    document.getElementById('fullName').value = data.fullName || '';
                    document.getElementById('email').value = data.email || '';
                    document.getElementById('phoneNumber').value = data.phoneNumber || '';
                    document.getElementById('address').value = data.address || '';
                    document.getElementById('city').value = data.city || '';
                    document.getElementById('postalCode').value = data.postalCode || '';
                    document.getElementById('cardNumber').value = data.cardNumber || '';
                    document.getElementById('cardHolderName').value = data.cardHolderName || '';
                    document.getElementById('expiryMonth').value = data.expiryMonth || '';
                    document.getElementById('expiryYear').value = data.expiryYear || '';
                    document.getElementById('cvv').value = data.cvv || '';
                }
            })
            .catch(error => {
                console.error('Error loading checkout info:', error);
            });
    } else {
        const form = document.getElementById('checkoutForm');
        if (form) {
            form.reset();
        }
    }
}

function processCheckout() {
    const form = document.getElementById('checkoutForm');
    if (!form) return;

    const formData = new FormData(form);

    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }

    const submitBtn = document.querySelector('button[onclick="processCheckout()"]');
    if (submitBtn) {
        const originalText = submitBtn.innerHTML;
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>İşleniyor...';
        submitBtn.disabled = true;

        const saveInfo = document.getElementById('saveInfo');
        const savedCheckoutInfoId = document.getElementById('savedCheckoutInfo');

        if (saveInfo && saveInfo.checked && (!savedCheckoutInfoId || !savedCheckoutInfoId.value)) {
            const checkoutData = {
                fullName: formData.get('fullName'),
                email: formData.get('email'),
                phoneNumber: formData.get('phoneNumber'),
                address: formData.get('address'),
                city: formData.get('city'),
                postalCode: formData.get('postalCode'),
                cardNumber: formData.get('cardNumber'),
                cardHolderName: formData.get('cardHolderName'),
                expiryMonth: formData.get('expiryMonth'),
                expiryYear: formData.get('expiryYear'),
                cvv: formData.get('cvv'),
                isDefault: true
            };

            fetch('/checkout/save', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams(checkoutData)
            })
                .then(response => response.text())
                .then(result => {
                    if (result === 'success') {
                        processPayment(savedCheckoutInfoId ? savedCheckoutInfoId.value : null);
                    } else {
                        throw new Error('Failed to save checkout info');
                    }
                })
                .catch(error => {
                    console.error('Error saving checkout info:', error);
                    processPayment(savedCheckoutInfoId ? savedCheckoutInfoId.value : null);
                });
        } else {
            processPayment(savedCheckoutInfoId ? savedCheckoutInfoId.value : null);
        }

        function processPayment(checkoutInfoId) {
            fetch('/checkout/process', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({
                    checkoutInfoId: checkoutInfoId || ''
                })
            })
                .then(response => response.text())
                .then(result => {
                    if (result === 'empty_cart') {
                        alert('Sepetiniz boş!');
                        window.location.href = '/cart';
                    } else if (result === 'error' || result === 'invalid_info') {
                        alert('Ödeme işlemi sırasında bir hata oluştu. Lütfen tekrar deneyin.');
                    } else {
                        const receiptIdElement = document.getElementById('receiptId');
                        if (receiptIdElement) {
                            receiptIdElement.textContent = result;
                        }
                        const modal = new bootstrap.Modal(document.getElementById('successModal'));
                        modal.show();
                    }
                })
                .catch(error => {
                    console.error('Error processing checkout:', error);
                    alert('Ödeme işlemi sırasında bir hata oluştu. Lütfen tekrar deneyin.');
                })
                .finally(() => {
                    submitBtn.innerHTML = '<i class="fas fa-credit-card me-2"></i>Satın Al';
                    submitBtn.disabled = false;
                });
        }
    }
}

document.addEventListener('DOMContentLoaded', function () {
    if (document.getElementById('checkoutForm')) {
        initCheckout();
    }
});