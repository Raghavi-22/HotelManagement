document.getElementById('loginForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;

    const userData = {
        emailID: email,
        password: password
    };

    axios.post('http://localhost:8080/admin_login', userData)
        .then(function (response) {
            console.log('Response:', response.data);
            if (response.status === 200) {
                localStorage.setItem('token', response.data.token);
                window.location.href = '/admin';
            } else {
                alert('Login failed. Invalid Credentials.');
            }
        })
        .catch(function (error) {
           // Check if the error response is 401 Unauthorized or a similar code for invalid credentials
                       if (error.response && error.response.status === 401) {
                           alert('Login failed: Email or Password incorrect.');
                       } else {
                           alert('Login failed. Please try again later.');
                       }
        });
});
