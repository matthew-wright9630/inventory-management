import {
    getWarehouseDetails,
    getItemInformation,
    addActiveStorageBins,
} from "./script.js";

const parent = document.getElementById("storage-location-list");

function router() {
    const hash = window.location.hash;
    const hashParts = hash.split("/");

    let page = hashParts[1];
    let id = hashParts[2];

    if (page === "warehouses" && id) {
        showHideElements("warehouse-list");
        let children = document.getElementById("warehouse-list").children;
        document
            .getElementById("storage-location-list")
            .classList.remove("d-none");
        document.getElementById("storage-location-list").classList.add("row");
        document.getElementById("warehouse-buttons").classList.remove("d-none");
        document.getElementById("warehouse-buttons").classList.add("d-flex");

        Array.from(children).forEach((child) => {
            if (child.id === "warehouse-" + id) {
                addActiveStorageBins(id);
                child.classList.add("col-12");
                child.classList.add("mb-3");
                child.classList.remove("d-none");

                populateEditWarehouseForm(child);
            } else {
                child.classList.add("d-none");
            }
        });
    } else if (page === "warehouses" && !id) {
        showHideElements("warehouse-list");
        let children = document.getElementById("warehouse-list").children;

        parent.innerHTML = "";

        Array.from(children).forEach((child) => {
            child.classList.remove("col-12");
            child.classList.remove("mb-3");
            child.classList.remove("d-none");
        });
        document.getElementById("warehouse-buttons").classList.remove("d-flex");
    } else if (page === "items" && id) {
        showHideElements("item-list");
        let children = document.getElementById("item-list").children;

        Array.from(children).forEach((child) => {
            if (child.id === "item-" + id) {
                child.classList.remove("d-none");
            } else {
                child.classList.add("d-none");
            }
        });
    } else if (page === "items") {
        showHideElements("item-list");
        let children = document.getElementById("item-list").children;

        parent.innerHTML = "";

        Array.from(children).forEach((child) => {
            child.classList.remove("d-none");
        });
        document.getElementById("warehouse-buttons").classList.remove("d-flex");
    } else {
        document.getElementById("warehouse-buttons").classList.remove("d-flex");
        showHideElements("no-items");
    }
}

function showHideElements(id) {
    let children = document.getElementById("section-container").children;
    Array.from(children).forEach((child) => {
        if (child.id === id) {
            document.getElementById(child.id).classList.add("row");
            document.getElementById(child.id).classList.remove("d-none");
        } else {
            document.getElementById(child.id).classList.add("d-none");
        }
    });
}

function populateEditWarehouseForm(element) {
    const text = element.children[1].innerText;
    const fullAddress = text.split(",").map((part) => part.trim());

    const title = element.children[0].innerText;
    const address = fullAddress[0];
    const addressLineTwo = fullAddress[1];
    const stateOrRegion = fullAddress[2];
    const country = fullAddress[3];

    const maxCapacityText = element.children[3].innerText;
    const maxCapacity = Number(maxCapacityText.split(":")[1].trim());

    const editWarehouseName = document.getElementById("edit-warehouse-name");
    const editWarehouseCountry = document.getElementById(
        "edit-warehouse-country"
    );
    const editWarehousestateOrRegion = document.getElementById(
        "edit-warehouse-state"
    );
    const editWarehousemaxCapacity = document.getElementById(
        "edit-warehouse-max-capacity"
    );
    const editWarehouseAddress = document.getElementById(
        "edit-warehouse-address"
    );
    const editWarehouseAddressLineTwo = document.getElementById(
        "edit-warehouse-address-line-two"
    );

    editWarehouseName.value = title;
    editWarehouseCountry.value = country;
    editWarehousestateOrRegion.value = stateOrRegion;
    editWarehousemaxCapacity.value = maxCapacity;
    editWarehouseAddress.value = address;
    editWarehouseAddressLineTwo.value =
        addressLineTwo === null ? "" : addressLineTwo;
}

window.addEventListener("hashchange", router);
window.addEventListener("load", () => {
    getWarehouseDetails();
    getItemInformation();
    router();
});
