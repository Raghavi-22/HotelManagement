function edit(field) {
    const inputElement = document.getElementById(field);
    const editButton = inputElement.nextElementSibling;
    const saveButton = editButton.nextElementSibling;

    // Make the input field editable
    inputElement.removeAttribute('readonly');

    // For the "Gender" field, change the <select> to a dropdown
    if (field === 'sex') {
        const selectElement = document.getElementById('sex');
        selectElement.removeAttribute('readonly');
    }
    if (field === 'password') {
        const passwordInput = document.getElementById('password');
        passwordInput.type = 'text';
    }

    // Hide the "Edit" button and show the "Save" button
    editButton.classList.add('hidden');
    saveButton.classList.remove('hidden');
}

function save(field) {
    const inputElement = document.getElementById(field);
    const editButton = inputElement.nextElementSibling;
    const saveButton = editButton.nextElementSibling;
    const updatedValue = document.getElementById(field).value;

    const apiUrl = 'http://localhost:8080/editProfile';

    const data = {
        attributeName: field,
        attributeValue: updatedValue
    };

    axios.put(apiUrl, data, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
        }
    })
        .then(response => {
            console.log(response);
            alert(`${field} updated successfully`);
        })
        .catch(error => {
            console.error(`Error updating ${field}:`, error);
            alert(`Failed to update ${field}. Please try again.`);
        });

    // Make the input field read-only
    inputElement.setAttribute('readonly', true);

    // For the "Sex" field, make the <select> read-only
    if (field === 'sex') {
        const selectElement = document.getElementById('sex');
        selectElement.setAttribute('readonly', true);
    }

    // Hide the "Save" button and show the "Edit" button
    editButton.classList.remove('hidden');
    saveButton.classList.add('hidden');
}

function changePassword() {
    const nPass = document.getElementById("n_pass").value;
    const cPass = document.getElementById("c_pass").value;

    if (cPass !== nPass) {
        alert("Passwords do not match.");
    } else {
        const apiUrl = 'http://localhost:8080/changePassword';
        const data = {
            newPassword: cPass
        };

        axios.put(apiUrl, data, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        })
            .then(response => {
                console.log(response);
                alert('Password updated successfully');
            })
            .catch(error => {
                console.error('Error updating password:', error);
                alert('Failed to update password. Please try again.');
            });
    }
}

function fetchUserProfile() {
    const apiUrl = 'http://localhost:8080/getProfile';
      const token = localStorage.getItem('token');
       if (!token) {
             alert('User not logged in. Redirecting to login page.');
             window.location.href = '/login'; // Redirect to staff login page
             return;
         }

    axios.get(apiUrl, {
        headers: {
            Authorization: `Bearer ${token }`
        }
    })
        .then(response => {
            const userData = response.data;
            document.getElementById('fName').value = userData.fname;
            document.getElementById('mName').value = userData.mname;
            document.getElementById('lName').value = userData.lname;
            document.getElementById('flatNo').value = userData.flatNo;
            document.getElementById('city').value = userData.city;
            document.getElementById('state').value = userData.state;
            document.getElementById('pin').value = userData.pin;
            document.getElementById('phoneNo').value = userData.phoneNo;
            document.getElementById('sex').value = userData.sex;
            document.getElementById('dob').value = userData.dob;
            document.getElementById('pEmail').value = userData.pEmail;
            document.getElementById('score').value = userData.score;
        })
        .catch(error => {
        alert('please log in u are token has expired');
        window.href.loc('/login');
            console.error('Error fetching user profile data:', error);
        });
}

window.addEventListener('load', fetchUserProfile);

function logOutUser() {
    localStorage.removeItem('token');
    axios
        .get('http://localhost:8080/logout')
        .then(() => {
            console.log('User logged out successfully');
            window.location.href = '/login';
        })
        .catch(error => {
            console.error('Error logging out:', error);
        });
}
function checkuser() {
        const token = localStorage.getItem('token'); // Fetch the token

           // Check if the token is available
           if (!token) {
               alert('User not logged in. Redirecting to login page.');
               window.location.href = '/login'; // Redirect to staff login page
               return;
           }
    }

