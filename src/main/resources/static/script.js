document.addEventListener('DOMContentLoaded', function () {
    const loanForm = document.getElementById('loanForm');

    loanForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = new FormData(loanForm);

        // Convert form data to JSON object
        const applicantInfo = {};
        formData.forEach((value, key) => {
            applicantInfo[key] = value;
        });

        // Make an API request to submit the application
        fetch('/application/v1/data', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(applicantInfo),
        })
        .then(response => response.text())
        .then(message => {
            alert(message); // Display the response message
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
});