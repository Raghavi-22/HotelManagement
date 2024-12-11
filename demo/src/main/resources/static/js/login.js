document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent the form from submitting normally
    const email = document.getElementById("pEmail").value; // Ensure this matches the input field ID
    const password = document.getElementById("password").value;

    // Create user data object
    const userData = {
        pEmail: email, // Ensure the key matches the backend field name
        password: password
    };

    console.log("Sending login request with data:", userData);


    axios.post('http://localhost:8080/login', userData)
        .then(function (response) {
            localStorage.setItem('token', response.data.token);
            console.log(response);
            console.log("ddd");
            // Check the response and redirect as needed
            if (response.status === 200) {
                window.location.href = '/dashboard';
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
