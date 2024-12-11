const fetchBookings = () => {
    const token = localStorage.getItem('token'); // Retrieve the token


           // Check if the token is available
           if (!token) {
               alert('User not logged in. Redirecting to login page.');
               window.location.href = '/login'; // Redirect to staff login page
               return;
           }

    axios.get('/getbookings', { headers: { Authorization: `Bearer ${token}` } })
        .then(response => {
            populateTable(response.data);
        })
        .catch(error => {
            console.error("Error fetching bookings:", error);
            alert("Failed to fetch bookings. Please try again later.");
        });
};

// Populate the table with booking data
const populateTable = (bookings) => {
    const bookingTable = document.getElementById("bookingTable");
    bookingTable.innerHTML = "";
    if (bookings.length === 0) {
        bookingTable.innerHTML = "<tr><td colspan='9'>No bookings available for this user.</td></tr>";
        return;
    }

    const today = new Date();

    bookings.forEach(booking => {
        const row = document.createElement("tr");
        const checkInDate = new Date(booking.checkInDate);
        const isCancelable = checkInDate > today;

        row.innerHTML = `
            <td>${booking.bookingID}</td>
            <td>${formatDate(booking.checkInDate)}</td>
            <td>${formatDate(booking.checkOutDate)}</td>
            <td>${formatDate(booking.date)}</td>
            <td>${formatTime(booking.time)}</td>
            <td>${booking.typeName}</td>
            <td>${booking.roomNo}</td>
            <td>${booking.cost}</td>
            <td>
                <button class="btn btn-danger btn-sm"
                        onclick="goToCancellationPage('${booking.bookingID}')"
                        ${isCancelable ? "" : "disabled"}>
                    Cancel Booking
                </button>
            </td>
        `;

        bookingTable.appendChild(row);
    });
};

const fetchserviceBookings = () => {
    const token = localStorage.getItem('token');
    if (!token) {
        console.error("No token found in local storage.");
        return;
    }

    axios.get('/getservicebookings', { headers: { Authorization: `Bearer ${token}` } })
        .then(response => {
            populateserviceTable(response.data);
        })
        .catch(error => {
            console.error("Error fetching service bookings:", error);
            alert("Failed to fetch service bookings. Please try again later.");
        });
};

const populateserviceTable = (bookings) => {
    const bookingTable = document.getElementById("servicebookingTable");
    bookingTable.innerHTML = "";
    if (bookings.length === 0) {
        bookingTable.innerHTML = "<tr><td colspan='7'>No service bookings available for this user.</td></tr>";
        return;
    }

    const today = new Date();

    bookings.forEach(booking => {
        const row = document.createElement("tr");
        const checkInDate = new Date(booking.date);
        const isCancelable = checkInDate > today;

        row.innerHTML = `
            <td>${booking.bookingID}</td>
            <td>${formatDate(booking.date)}</td>
             <td>${formatTime(booking.time)}</td>
            <td>${formatDate(booking.bookingDate)}</td>
            <td>${booking.typeName}</td>
            <td>${booking.cost}</td>
            <td>
                <button class="btn btn-danger btn-sm"
                        onclick="goToCancellationPage('${booking.bookingID}')"
                        ${isCancelable ? "" : "disabled"}>
                    Cancel Booking
                </button>
            </td>
        `;

        bookingTable.appendChild(row);
    });
};

// Format date to display in a readable format
const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleDateString();
};

// Format time to display in a readable format
const formatTime = (timeString) => {
    if (!timeString) return ""; // Return empty if timeString is undefined or null
    const [hours, minutes] = timeString.split(':');
    return `${hours}:${minutes}`;
};

// Redirect to cancellation page with booking ID
const goToCancellationPage = (bookingID) => {
    window.location.href = `/cancellation?bookingID=${encodeURIComponent(bookingID)}`;
};

// Fetch bookings on page load
document.addEventListener("DOMContentLoaded", () => {
    fetchBookings();
    fetchserviceBookings();
});

//function checkuser() {
//        const token = localStorage.getItem('token'); // Fetch the token
//
//           // Check if the token is available
//           if (!token) {
//               alert('User not logged in. Redirecting to login page.');
//               window.location.href = '/login'; // Redirect to staff login page
//               return;
//           }
//    }
//window.addEventListener('load', checkuser);
