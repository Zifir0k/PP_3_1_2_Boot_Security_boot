const addNewUserForm = document.querySelector("#addForm")

addNewUserForm.addEventListener('submit', (e) => {
        e.preventDefault()

        fetch(urlUsers, {
            method: "POST",
            headers: {"Content-type": "application/json"},
            body: JSON.stringify({
                firstName: document.querySelector("#userFirstNameAdd").value,
                lastName: document.querySelector("#userLastNameAdd").value,
                age: document.querySelector("#userAgeAdd").value,
                email: document.querySelector("#userEmailAdd").value,
                password: document.querySelector("#userPasswordAdd").value,
                roles: [...document.querySelector("#roleToAdd")]
                    .filter(option => option.selected)
                    .map(option => option.value)
            })
        })
            .then(() => {
                addNewUserForm.reset()
                document.getElementById("users-table").click()
                printUsersTable()
            })
    }
)
