document.getElementById('employeeForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const firstName = document.getElementById('fName').value;
    const lastName = document.getElementById('lName').value;
    const pEmail = document.getElementById('pEmail').value;
    const belongsto = document.getElementById('belongsto').value; // New field
    const city = document.getElementById('city').value;
    const state = document.getElementById('state').value;
    const pincode = document.getElementById('pincode').value;
    const gender = document.getElementById('gender').value;
    const aadhaarNo = document.getElementById('aadhaarNo').value;
    const salary = document.getElementById('salary').value;
//    const role = document.getElementById('role').value;
    const position = document.getElementById('position').value; // New field
    const accountNo = document.getElementById('accountNo').value;
    const IFSCCode = document.getElementById('IFSCCode').value;
    const bankName = document.getElementById('bankName').value;
    const password = document.getElementById('password').value; // New field

    const userData = {
        firstName: firstName,
        lastName: lastName,
        pEmail: pEmail,
        belongsto: belongsto,
        city: city,
        state: state,
        pin: pincode,
        sex: gender,
        aadhaarNo: aadhaarNo,
        salary: salary,
//        role: role,
        position: position,
        accountNo: accountNo,
        ifsccode: IFSCCode,
        bankName: bankName,
        password: password // Added password
    };

    console.log(userData);

    axios.post('http://localhost:8080/admin/addEmployee', userData,{ headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }})
        .then(function (response) {
            console.log(response.data);
            console.log(response.data.localeCompare('fail'));
            const i = response.data.localeCompare('fail');
            console.log(response);
            if (i === 0) {
                alert('Employee already exists!');
            } else {
                // Registration was successful; redirect to the login page
                window.location.href = '/admin/allEmployees'; // Replace with the actual URL of your login page
            }
        })
        .catch(function (error) {
            console.error('There was an error!', error);
            alert('Invalid Input or Registration Failed!');
            // Handle navigation to an error page or other actions as needed
        });
});

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