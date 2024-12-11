function getUrlParameters() {
    const params = new URLSearchParams(window.location.search);
    return {
        roomReqId: params.get('roomRequestID'),
        cost: params.get('cost')
    };
}

window.onload = function() {
    const { roomReqId, cost } = getUrlParameters();
    document.getElementById('cost').value = cost;
    document.getElementById('roomReqId').value = roomReqId;


    // Fetch available discounts for the user
    fetchUserDiscounts(cost);
};

function fetchUserDiscounts(originalCost) {
    axios.get('/discount', {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
    })
    .then(response => {
        const discounts = response.data;
        const discountSelect = document.getElementById('discountSelect');

        // Clear existing options in the select element (if any)
        discountSelect.innerHTML = '<option value="">Select Discount</option>';

        discounts.forEach(discount => {
            const option = document.createElement('option');
            option.value = discount.discountId; // Adjust according to your discount object structure
            option.textContent = discount.description;
            console.log(option.value);
            console.log(option.textContent);
            // Adjust according to your discount object structure
            discountSelect.appendChild(option);
        });

        // Add an onchange event listener to apply discount when selected
        discountSelect.onchange = function() {
            const discountName = discountSelect.value;
            if (discountName) {
                applyDiscount(discountName, originalCost);
                console.log(discountName);
            } else {
                // Reset cost if no discount is selected
                document.getElementById('cost').value = originalCost;
            }
        };
    })
    .catch(error => {
        console.error('Error fetching discounts:', error);
    });
}

function applyDiscount(discountName, originalCost) {
    const data = {
        discountID: discountName,
        cost: originalCost
    };


axios.post('/getamount', data, {
    headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
})
.then(response => {
    const discountValue = Number(response.data.cost); // Access newCost from the response
    if (!isNaN(discountValue)) {
        document.getElementById('cost').value = discountValue.toFixed(2);
    } else {
        console.error('Invalid discount value received:', response.data);
    }
})
.catch(error => {
    console.error('Error applying discount:', error);
});
}
function submitPayment() {
    const roomReqId = document.getElementById('roomReqId').value;
    const cost = document.getElementById('cost').value;
    const paymentMode = document.getElementById('paymentMode').value;
    const transactionId = document.getElementById('transactionId').value;
    const discountId = document.getElementById('discountSelect').value;

    // Prepare the payment data to send to the server
    const paymentData = {
        roomReqId: roomReqId,
        cost: parseFloat(cost),
        paymentMode: paymentMode,
        transactionId: transactionId,
        discountId: discountId,
    };

    // Make a POST request to submit the payment
    axios.post('/dopayment', paymentData, {
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
