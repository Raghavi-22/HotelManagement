function fetchEmployees() {
    const apiUrl = "http://localhost:8080/admin/getAllEmployees";
    axios.get(apiUrl,{ headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }})
        .then((response) => {
            console.log(response);
            const data = response.data;
            console.log(data);
            const employeeTableBody = document.getElementById('employeeTableBody');
            employeeTableBody.innerHTML = '';

            data.forEach((employee, index) => {
                const row = document.createElement('tr');

                row.innerHTML = `
                    <th scope="row">${index + 1}</th>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.belongsto}</td>
                    <td>${employee.state}</td>
                    <td>${employee.city}</td>
                    <td>${employee.pin}</td>
                    <td>${employee.sex}</td>
                    <td>${employee.aadhaarNo}</td>
                    <td>${employee.pEmail}</td>
                    <td>${employee.position}</td>
                    <td>${employee.salary}</td>
                    <td>${employee.accountNo}</td>
                    <td>${employee.ifsccode}</td>
                    <td>${employee.bankName}</td>
                `;
                employeeTableBody.appendChild(row);
            });
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

window.addEventListener('load', fetchEmployees);