function fetchCustomers() {
    const apiUrl = "http://localhost:8080/admin/allCustomers";
    axios.get(apiUrl,{ headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }})
        .then((response) => {
            console.log(response);
            const data = response.data;
            console.log(data);
            const customerTableBody = document.getElementById('customerTableBody');
            customerTableBody.innerHTML = '';

            data.forEach((customer, index) => {
                const row = document.createElement('tr');

                row.innerHTML = `
                    <th scope="row">${index + 1}</th>
                    <td>${customer.fname}</td> <!-- First Name -->
                    <td>${customer.mname || ''}</td> <!-- Middle Name -->
                    <td>${customer.lname}</td> <!-- Last Name -->
                    <td>${customer.flatNo}</td> <!-- Flat Number -->
                    <td>${customer.state}</td> <!-- State -->
                    <td>${customer.city}</td> <!-- City -->
                    <td>${customer.pin}</td> <!-- Pin Code -->
                    <td>${customer.sex}</td> <!-- Gender -->
                    <td>${customer.phoneNo}</td> <!-- Phone Number -->
                    <td>${customer.pEmail}</td> <!-- Personal Email -->
                    <td>${customer.score}</td> <!-- Score -->
                    <td>${new Date(customer.dob).toLocaleDateString()}</td> <!-- Date of Birth -->
                `;
                customerTableBody.appendChild(row);
            })
        })
        .catch(error => {
            console.error('Error fetching data: ', error);
        });
}

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

window.addEventListener('load', fetchCustomers);
