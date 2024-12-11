function homeFunc() {
    const token = localStorage.getItem('token');

    const loginButton = document.getElementById('loginButton');
    const logoutButton = document.getElementById('logoutButton');
    const profileButton = document.getElementById('profileButton');

    if (token) {
      logoutButton.style.display = 'block';
      loginButton.style.display = 'none';
      profileButton.style.display = 'block';
    } else {
      loginButton.style.display = 'block';
      logoutButton.style.display = 'none';
      profileButton.style.display = 'none';
    }
}

window.addEventListener('load', homeFunc());

function logOutUser() {
    localStorage.removeItem('token');
    axios
        .get('http://localhost:8080/logout')
        .then((response) => {
          console.log('User logged out successfully');
          window.location.href = '/staff-login';
        })
        .catch((error) => {
          console.error('Error logging out:', error);
        });
    }