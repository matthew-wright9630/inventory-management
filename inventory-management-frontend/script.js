import {
    getAllWarehouses,
    getActiveStorageBinsInWarehouse,
    getAllItems,
    getAllItemsByItemDetailId,
    getAllLotNumbersByItemId,
    getQuantityOfItemId,
    getItemsByItemName,
} from "./api.js";

let listOfItems = [];

// document
//     .getElementById("warehouse-test")
//     .addEventListener("click", getWarehouseDetails);

export function getWarehouseDetails() {
    getAllWarehouses().then((warehouseList) => {
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
    let warehouseDiv = document.createElement("div");
    let titleEl = document.createElement("h2");
    let addressEl = document.createElement("p");
    let capacityEl = document.createElement("p");
    let maxCapacityEl = document.createElement("p");
    let isActiveEl = document.createElement("p");

    warehouseDiv.classList.add(
        "col-4",
        "border",
        "rounded",
        "card",
        "bg-primary"
    );
    warehouseDiv.id = `warehouse-${newWarehouse.id}`;
    titleEl.innerText = newWarehouse.name;
    addressEl.innerText = `${newWarehouse.address} ${
        newWarehouse.addressLineTwo
    } ${newWarehouse.location?.stateOrRegion ?? ""}, ${
        newWarehouse.location?.country ?? ""
    }`;
    maxCapacityEl.innerText =
        "Maximum capacity: " + newWarehouse.maximumCapacity;
    capacityEl.innerText = "Current capacity: " + activeStorageBins.length;
    console.log(newWarehouse.active);
    if (newWarehouse.active) {
        isActiveEl.innerText = "Warehouse is active";
    } else {
        isActiveEl.innerText = "Warehouse has been deactivated";
    }

    warehouseDiv.appendChild(titleEl);
    warehouseDiv.appendChild(addressEl);
    warehouseDiv.appendChild(capacityEl);
    warehouseDiv.appendChild(maxCapacityEl);
    warehouseDiv.appendChild(isActiveEl);

    document.getElementById("warehouse-list").appendChild(warehouseDiv);
    document
        .getElementById(`warehouse-${newWarehouse.id}`)
        .addEventListener("click", () => {
            window.location.hash = "#/warehouses/" + newWarehouse.id;
        });
}

//gets the list of item details, then finds the quantity in the network.
//also returns the quantity of the item at each warehouse
export function getItemInformation() {
    getAllItems()
        .then((itemDetailList) => {
            itemDetailList.map((itemDetail) => {
                getQuantityOfItemId(itemDetail.id).then((quantity) => {
                    addItemDetailsToList(itemDetail, quantity);
                });
            });
        })
        .catch((err) => console.error(err));
}

function addItemDetailsToList(itemDetail, itemQuantityObject) {
    console.log(itemDetail, itemQuantityObject);
    let itemDiv = document.createElement("div");
    let titleEl = document.createElement("h2");
    let skuEl = document.createElement("p");
    let descriptionEl = document.createElement("p");
    let shelfLifeEl = document.createElement("p");
    let quantityEl = document.createElement("p");

    itemDiv.id = `item-${itemDetail.id}`;
    titleEl.innerText = itemDetail.name;
    skuEl.innerText = "SKU #: " + itemDetail.sku;
    descriptionEl.innerText = "Description: " + itemDetail.description;
    shelfLifeEl.innerText = "Shelf Life: " + itemDetail.shelfLife;
    quantityEl.innerText =
        "Quantity of item in network: " + itemQuantityObject.quantity;

    itemDiv.appendChild(titleEl);
    itemDiv.appendChild(skuEl);
    itemDiv.appendChild(descriptionEl);
    itemDiv.appendChild(shelfLifeEl);
    itemDiv.appendChild(quantityEl);

    itemDiv.classList.add(
        "container-fluid",
        "col-4",
        "border",
        "rounded",
        "card",
        "bg-primary"
    );

    document.getElementById("item-list").appendChild(itemDiv);
    document
        .getElementById(`item-${itemDetail.id}`)
        .addEventListener("click", () => {
            window.location.hash = "#/items/" + itemDetail.id;
        });
}

function addActiveStorageBins(warehouseId) {
    getActiveStorageBinsInWarehouse(warehouse.id).then((activeStorageBins) => {
        addWarehouseToList(warehouse, activeStorageBins);
    });
}

document.getElementById("search-btn").addEventListener("click", () => {
    let text = document.getElementById("search-input").value;
    getItemsByItemName(text).then((itemDetail) => {
        if (itemDetail.length === 0) {
            window.location.hash = "#/no-item";
        } else {
            let children = document.getElementById("item-list").children;

            document.getElementById("item-list").classList.add("row");
            document.getElementById("item-list").classList.remove("d-none");
            document.getElementById("warehouse-list").classList.remove("row");
            document.getElementById("warehouse-list").classList.add("d-none");

            Array.from(children).forEach((child) => {
                if (child.id === "item-" + itemDetail.id) {
                    child.classList.remove("d-none");
                    window.location.hash = "#/items/" + itemDetail.id;
                } else {
                    child.classList.add("d-none");
                }
            });
        }
    });
    document.getElementById("search-input").value = "";
});
