document.addEventListener("DOMContentLoaded", function() {
    const deleteButton = document.getElementById("deleteButton");
    deleteButton.addEventListener("click", deleteEmployee);

    function deleteEmployee() {
        const employeeEmail = document.getElementById("employeeEmail").value;
        const apiUrl = "http://localhost:8080/admin/deleteEmployee";
        const token = localStorage.getItem('token');

        // Check if the user is logged in
        if (!token) {
            alert('User not logged in. Redirecting to login page.');
            window.location.href = '/adminLoginPage';
            return;
        }

        const data = { pEmail: employeeEmail };

        // Send the delete request
        axios.post(apiUrl, data, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        .then(response => {
            alert(response.data);  // Display response message from server
            if (response.data.includes("Deleted")) {
                window.location.reload();  // Reload the page if deletion was successful
            }
        })
        .catch(error => {
            alert("Error deleting employee. Please try again.");
            console.error("Error deleting employee: ", error);
        });
    }
});
