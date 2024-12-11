document.addEventListener("DOMContentLoaded", function () {
    // Fetch and display images for Hotel 1
    fetch('/feedback/images/1', { cache: 'no-store' })

        .then(response => response.json())
        .then(images => {
            const imageGallery1 = document.getElementById("imageGallery1");
            images.forEach(image => {
                const col = document.createElement("div");
                col.className = "col-md-4 mb-4"; // Bootstrap 4: 3 columns per row
                const imageUrl = `/images/1/${image}?timestamp=${new Date().getTime()}`;

                console.log(imageUrl); // Log the image URL
                col.innerHTML = `
                    <div class="card h-100">
                        <img src="${imageUrl}" class="card-img-top" alt="Feedback Image">
                    </div>
                `;
                imageGallery1.appendChild(col);
            });
        })
        .catch(error => {
            console.error("Error fetching images for hotel 1:", error);
        });

    // Fetch and display images for Hotel 2
    fetch('/feedback/images/2')
        .then(response => response.json())
        .then(images => {
            const imageGallery2 = document.getElementById("imageGallery2");
images.forEach(image => {
    const col = document.createElement("div");
    col.className = "col-md-4 mb-4"; // Bootstrap 4: 3 columns per row
    const imageUrl = `/images/2/${image}`; // Construct the image URL
    console.log(imageUrl); // Log the image URL
    col.innerHTML = `
        <div class="card h-100">
            <img src="${imageUrl}" class="card-img-top" alt="Feedback Image">
        </div>
    `;
    imageGallery2.appendChild(col);
});

        })
        .catch(error => {
            console.error("Error fetching images for hotel 2:", error);
        });
});
