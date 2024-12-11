document.getElementById('registerForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const user = {
        fname: document.getElementById('fname').value,
        mname: document.getElementById('mname').value,
        lname: document.getElementById('lname').value,
        flatNo: document.getElementById('flatNo').value,
        sex: document.getElementById('sex').value,
        dob: document.getElementById('dob').value,
        city: document.getElementById('city').value,
        state: document.getElementById('state').value,
        pin: document.getElementById('pin').value,
        phoneNo: document.getElementById('phoneNo').value,
        password: document.getElementById('password').value,
        pEmail: document.getElementById('pEmail').value
    };

    axios.post('http://localhost:8080/register', user)
        .then(function (response) {
            console.log('Response:', response.data);
            if (response.status === 201) {
                alert('Registration successful!');
                window.location.href = '/login';
            } else {
                alert('Registration failed. Please try again.');
            }
        })
        .catch(function (error) {
            console.error('There was an error!', error);
            alert('Registration failed. Please try again later.');
        });
});
