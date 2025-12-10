const URL = "http://localhost:8080";

function getAllWarehousesAndStorageBins() {
    return fetch(URL + "/warehouses", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => {
            console.error(err);
        });
}

function getActiveStorageBinsInWarehouse(warehouseId) {
    return fetch(`${URL}/storage-bin/warehouse/${warehouseId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => {
            console.error(err);
        });
}

export { getAllWarehousesAndStorageBins, getActiveStorageBinsInWarehouse };
