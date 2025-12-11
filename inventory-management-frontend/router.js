import { getWarehouseDetails, getItemInformation } from "./script.js";

function router() {
    const hash = window.location.hash;
    const hashParts = hash.split("/");

    let page = hashParts[1];
    let id = hashParts[2];

    if (page === "warehouses" && id) {
        let children = document.getElementById("warehouse-list").children;

        document.getElementById("item-list").classList.remove("row");
        document.getElementById("item-list").classList.add("d-none");
        document.getElementById("warehouse-list").classList.add("row");
        document.getElementById("warehouse-list").classList.remove("d-none");
        document.getElementById("no-items").classList.add("d-none");

        Array.from(children).forEach((child) => {
            if (child.id === "warehouse-" + id) {
                child.classList.add("col-12");
                child.classList.remove("d-none");
            } else {
                child.classList.add("d-none");
            }
        });
    } else if (page === "items" && id) {
        let children = document.getElementById("item-list").children;

        document.getElementById("item-list").classList.add("row");
        document.getElementById("item-list").classList.remove("d-none");
        document.getElementById("warehouse-list").classList.remove("row");
        document.getElementById("warehouse-list").classList.add("d-none");
        document.getElementById("no-items").classList.add("d-none");

        Array.from(children).forEach((child) => {
            if (child.id === "item-" + id) {
                child.classList.remove("d-none");
            } else {
                child.classList.add("d-none");
            }
        });
    } else if (page === "items") {
        let children = document.getElementById("item-list").children;

        document.getElementById("item-list").classList.add("row");
        document.getElementById("item-list").classList.remove("d-none");
        document.getElementById("warehouse-list").classList.remove("row");
        document.getElementById("warehouse-list").classList.add("d-none");
        document.getElementById("no-items").classList.add("d-none");

        Array.from(children).forEach((child) => {
            child.classList.remove("d-none");
        });
    } else if (page === "warehouses") {
        let children = document.getElementById("warehouse-list").children;

        document.getElementById("item-list").classList.remove("row");
        document.getElementById("item-list").classList.add("d-none");
        document.getElementById("warehouse-list").classList.add("row");
        document.getElementById("warehouse-list").classList.remove("d-none");
        document.getElementById("no-items").classList.add("d-none");

        Array.from(children).forEach((child) => {
            child.classList.remove("d-none");
        });
    } else {
        document.getElementById("warehouse-list").classList.add("row");
        document.getElementById("item-list").classList.add("d-none");
        document.getElementById("no-items").classList.remove("d-none");
    }
}

// document.getElementById("warehouse-btn").addEventListener("click", () => {
//     window.location.hash = "#/warehouses";
// });

// document.getElementById("item-btn").addEventListener("click", () => {
//     window.location.hash = "#/items";
// });

window.addEventListener("hashchange", router);
window.addEventListener("load", () => {
    getWarehouseDetails();
    getItemInformation();
    router();
});
