function maskEmail(email) {
    var atIndex = email.indexOf('@');
    var localPart = email.slice(0, atIndex);
    var domainPart = email.slice(atIndex);

    if (localPart.length <= 3) {
        return localPart.charAt(0) + '*'.repeat(localPart.length - 1) + domainPart;
    } 
    else {
        var visibleCount = Math.ceil(localPart.length / 2);
        var maskedCount = localPart.length - visibleCount;
        var maskedLocalPart = localPart.slice(0, visibleCount) + '*'.repeat(maskedCount);
        return maskedLocalPart + domainPart;
    }
}

document.addEventListener("DOMContentLoaded", function() {
    var emailElement = document.getElementById('maskedEmail');
    if (emailElement) {
        var email = emailElement.getAttribute('data-email');
        if (email) {
            emailElement.textContent = maskEmail(email);
        } else {
            console.error("Email attribute is missing.");
        }
    } else {
        console.error("Element with id 'maskedEmail' not found.");
    }
});
