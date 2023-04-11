let selectOptionRoles = ``
sendRequest('GET', "/api/roles")
    .then(data => {
            let allRoles = data.map(el => el["role"])
            for (const role of allRoles) {
                selectOptionRoles += `<option value="${role}">${role}</option>`
            }
            document.querySelector("#roleToAdd").innerHTML = selectOptionRoles
        }
    )