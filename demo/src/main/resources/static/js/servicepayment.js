function getUrlParameters() {
    const params = new URLSearchParams(window.location.search);
    return {
        roomReqId: params.get('serviceRequestID'),
        cost: params.get('cost')
    };
}

window.onload = function() {
    const { roomReqId, cost } = getUrlParameters();
    document.getElementById('cost').value = cost;
    document.getElementById('roomReqId').value = roomReqId;


};
function submitPayment() {
    const roomReqId = document.getElementById('roomReqId').value;
    const cost = document.getElementById('cost').value;
    const paymentMode = document.getElementById('paymentMode').value;
    const transactionId = document.getElementById('transactionId').value;

    // Prepare the payment data to send to the server
    const paymentData = {
        roomReqId: roomReqId,
        cost: parseFloat(cost),
        paymentMode: paymentMode,
        transactionId: transactionId,
        discountId: 0,
    };

    // Make a POST request to submit the payment
    axios.post('/doservicepayment', paymentData, {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
    })
    .then(response => {
        alert("Payment successful!");
        window.location.href = '/Bookings';
    })
    .catch(error => {
        alert("Payment has already been done on this id");
        console.error('Error during payment:', error);
    });

    return false; // Prevent form submission
}

function validatePaymentForm() {
    const paymentMode = document.getElementById("paymentMode").value;
    const amount = parseFloat(document.getElementById("amount").value);
    const date = document.getElementById("date").value;
    const time = document.getElementById("time").value;
    const transactionId = document.getElementById("transactionId").value;

    // Check if payment mode is selected
    if (!paymentMode) {
        alert("Please select a payment mode.");
        return false;
    }

    // Check if amount is valid
    if (isNaN(amount) || amount <= 0) {
        alert("Please enter a valid amount.");
        return false;
    }

    if (!transactionId.trim()) {
        alert("Transaction ID is a required field.");
        return false;
    }

    // If all checks pass, simulate payment processing
    processPayment();
    return false; // Prevent form from submitting traditionally
}

function processPayment() {
    alert("Payment processed successfully!");
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
window.addEventListener('load', checkuser);
