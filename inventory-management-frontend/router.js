import { getWarehouseDetails, getItemInformation } from "./script.js";

function router() {
    const hash = window.location.hash;
    const hashParts = hash.split("/");

    let page = hashParts[1];
    let id = hashParts[2];

    if (page === "warehouses" && id) {
    } else if (page === "items" && id) {
    } else if (page === "items") {
        document.getElementById("item-list").classList.add("row");
        document.getElementById("item-list").classList.remove("d-none");
        document.getElementById("warehouse-list").classList.remove("row");
        document.getElementById("warehouse-list").classList.add("d-none");
    } else {
        document.getElementById("item-list").classList.remove("row");
        document.getElementById("item-list").classList.add("d-none");
        document.getElementById("warehouse-list").classList.add("row");
        document.getElementById("warehouse-list").classList.remove("d-none");
    }
}

document.getElementById("home-btn").addEventListener("click", () => {
    window.location.hash = "#/";
});

document.getElementById("item-btn").addEventListener("click", () => {
    window.location.hash = "#/items";
});

window.addEventListener("hashchange", router);
window.addEventListener("load", () => {
    getWarehouseDetails();
    getItemInformation();
    router();
});
