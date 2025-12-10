import {
    getAllWarehousesAndStorageBins,
    getActiveStorageBinsInWarehouse,
} from "./api.js";

document
    .getElementById("warehouse-test")
    .addEventListener("click", getWarehouseDetails);

function getWarehouseDetails() {
    getAllWarehousesAndStorageBins().then((warehouseList) => {
        warehouseList.map((warehouse) => {
            getActiveStorageBinsInWarehouse(warehouse.id).then(
                (activeStorageBins) => {
                    addWarehouseToList(warehouse, activeStorageBins);
                }
            );
        });
    });
}

function addWarehouseToList(newWarehouse, activeStorageBins) {
    console.log(newWarehouse, activeStorageBins);
    let warehouseDiv = document.createElement("div");
    let titleEl = document.createElement("h2");
    let addressEl = document.createElement("p");
    let capacityEl = document.createElement("p");
    let maxCapacityEl = document.createElement("p");

    warehouseDiv.classList.add(
        "col-4",
        "border",
        "rounded",
        "warehouse-card",
        "bg-primary"
    );
    warehouseDiv.id = `warehouse-${newWarehouse.id}`;
    titleEl.innerText = newWarehouse.name;
    addressEl.innerText = `${newWarehouse.address} ${
        newWarehouse.addressLineTwo
    }, ${newWarehouse.location?.stateOrRegion ?? ""}, ${
        newWarehouse.location?.country ?? ""
    }`;
    maxCapacityEl.innerText =
        "Maximum capacity: " + newWarehouse.maximumCapacity;
    capacityEl.innerText = "Current capacity: " + activeStorageBins.length;

    warehouseDiv.appendChild(titleEl);
    warehouseDiv.appendChild(addressEl);
    warehouseDiv.appendChild(capacityEl);
    warehouseDiv.appendChild(maxCapacityEl);

    document.getElementById("warehouse-list").appendChild(warehouseDiv);
    document
        .getElementById(`warehouse-${newWarehouse.id}`)
        .addEventListener("click", () => {
            console.log(newWarehouse);
        });
}
