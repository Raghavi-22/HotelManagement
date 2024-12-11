// Get booking ID from URL
const urlParams = new URLSearchParams(window.location.search);
const bookingID = urlParams.get('bookingID');
document.getElementById('bookingID').value = bookingID;

// Handle cancellation form submission
document.getElementById('cancellationForm').addEventListener('submit', function(e) {
    e.preventDefault(); // Prevent default form submission
    const reason = document.getElementById('reason').value;

    // Send cancellation request to the server
    axios.post('/booking/cancel', {
        bookingID: bookingID,
        reason: reason
    },{ headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }})
    .then(response => {
        alert("Booking canceled successfully.");
        window.location.href = '/Bookings'; // Redirect back to the bill page
    })
    .catch(error => {
        alert("Failed to cancel booking.");
        console.error("Error canceling booking:", error);
    });
});
