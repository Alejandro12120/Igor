package es.alejandro12120.igor.utils;

import com.google.gson.JsonObject;

public class CosmeticItem {
    private String id;
    private String name;
    private String price;
    private String priceIcon;
    private String rarity;
    private String type;
    private String description;
    private String sortingRarity;
	private JsonObject images;

    public CosmeticItem() {
        super();
    }

    public CosmeticItem(String id, String name, String price, String priceIcon, JsonObject images, String rarity, String type) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.priceIcon = priceIcon;
		this.images = images;
        this.rarity = rarity;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceIcon() {
        return priceIcon;
    }

    public void setPriceIcon(String priceIcon) {
        this.priceIcon = priceIcon;
    }

	public JsonObject getImages() {
		return images;
	}
	
	public void setImages(JsonObject object) {
		this.images = object;
	}
	
    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSortingRarity() {
        return sortingRarity;
    }

    public void setSortingRarity(String sortingRarity) {
        this.sortingRarity = sortingRarity;
    }


}