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

    const apiUrl = 'http://localhost:8080/editStaffProfile';

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
        const apiUrl = 'http://localhost:8080/changeStaffPassword';
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
    const apiUrl = 'http://localhost:8080/getStaffProfile';
    const token = localStorage.getItem('token'); // Fetch the token

    // Check if the token is available
    if (!token) {
        alert('User not logged in. Redirecting to login page.');
        window.location.href = '/staff-login'; // Redirect to staff login page
        return;
    }

    axios.get(apiUrl, {
        headers: {
            Authorization: `Bearer ${token}` // Use the retrieved token
        }
    })
    .then(response => {
        const staffData = response.data;
        // Populate the form fields with the response data from Staff module
        document.getElementById('StaffID').value = staffData.staffID || '';
        document.getElementById('Belongsto').value = staffData.belongsto || '';
        document.getElementById('FirstName').value = staffData.firstName || '';
        document.getElementById('LastName').value = staffData.lastName || '';
        document.getElementById('Salary').value = staffData.salary || '';
        document.getElementById('Position').value = staffData.position || '';
        document.getElementById('Sex').value = staffData.sex || '';
        document.getElementById('City').value = staffData.city || '';
        document.getElementById('State').value = staffData.state || '';
        document.getElementById('PIN').value = staffData.pin || '';
        document.getElementById('email').value = staffData.pEmail || '';
        document.getElementById('aadhaarNo').value = staffData.aadhaarNo || '';
        document.getElementById('accountNo').value = staffData.accountNo || '';
        document.getElementById('IFSCCode').value = staffData.ifsccode || '';
    })
    .catch(error => {
        console.error('Error fetching staff profile data:', error);
    });
}

// Trigger fetchUserProfile when the window loads
window.addEventListener('load', fetchUserProfile);
