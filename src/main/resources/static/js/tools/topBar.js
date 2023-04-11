sendRequest("GET", "/api/user")
    .then(data => {
            document.querySelector("#topBar").innerHTML = `${data["email"]} with roles: ${data["roles"].map(el => el.role)}`
        }
    )