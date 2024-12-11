
function check() {
        const token = localStorage.getItem('token'); // Fetch the token

           // Check if the token is available
           if (!token) {
               alert('User not logged in. Redirecting to login page.');
               window.location.href = '/adminLoginPage'; // Redirect to staff login page
               return;
           }
    }
window.addEventListener('load', check);