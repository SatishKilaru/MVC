document.addEventListener("DOMContentLoaded", function() {
  const addPassengerButton = document.getElementById("addPassengerButton");
  const passengersTableBody = document.getElementById("passengersTableBody");
const fromSelect = document.getElementById("from");
  const toSelect = document.getElementById("to");
  const trainNumberSelect = document.getElementById("trainName");
  
  addPassengerButton.addEventListener("click", function() {
    const newRow = document.createElement("tr");
    newRow.innerHTML = `
      <td><input type="text" name="passengerName" placeholder="Name"style="width:70px;" required></td>
      <td><select name="passengerGender" style="width:40px;">
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">Other</option>
          </select></td>
      <td><input type="number" name="passengerAge" placeholder="Age" style="width:40px;"required></td>
      <td><button type="button" class="deletePassengerButton">Delete</button></td>
    `;
    passengersTableBody.appendChild(newRow);
  });
  
  passengersTableBody.addEventListener("click", function(event) {
    if (event.target.classList.contains("deletePassengerButton")) {
      const row = event.target.closest("tr");
      row.remove();
    }
  });

	const xhr = new XMLHttpRequest();
  xhr.open("GET", "DataLoader", true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) {
        const data = JSON.parse(xhr.responseText);
        const places = data.places;
        const trains = data.trains;

        // Populate the select elements
        places.forEach(place => {
          const option = document.createElement("option");
          option.value = place;
          option.textContent = place;
          fromSelect.appendChild(option);
          toSelect.appendChild(option.cloneNode(true));
        });

        trains.forEach(train => {
          const option = document.createElement("option");
          option.value = train;
          option.textContent = train;
          trainNumberSelect.appendChild(option);
        });
      } else {
        console.error("Error fetching data:", xhr.status, xhr.statusText);
      }
    }
  };
  xhr.send();
});


