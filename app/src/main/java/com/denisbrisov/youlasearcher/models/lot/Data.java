package com.denisbrisov.youlasearcher.models.lot;

import com.fasterxml.jackson.annotation.*;

public class Data {
    private String id;
    private String type;
    private Object linkedID;
    private String name;
    private String slug;
    private long price;
    private Object priceType;
    private String priceText;
    private long discount;
    private long discountedPrice;
    private long dateCreated;
    private long dateUpdated;
    private long datePublished;
    private Object dateSold;
    private Object dateBlocked;
    private Object dateDeleted;
    private long dateArchivation;
    private boolean isPublished;
    private boolean isSold;
    private boolean isDeleted;
    private boolean isBlocked;
    private boolean isArchived;
    private boolean isExpiring;
    private boolean isVerified;
    private boolean isPromoted;
    private long soldMode;
    private long archiveMode;
    private long blockMode;
    private long blockType;
    private Object[] blockTypes;
    private long category;
    private long subcategory;
    private Object dateFavorited;
    private long favoriteCounter;
    private long groupID;
    private String url;
    private String urlBranch;
    private String shortURL;
    private boolean contactsVisible;
    private ImageElement[] images;
    private DataLocation location;
    private long priceWithDiscountSeller;
    private long firePromoState;
    private boolean p2PCallRatingNeeded;
    private boolean isDefaultPhoto;
    private String blockTypeText;
    private boolean isFavorite;
    private String description;
    private long views;
    private String shareURL;
    private String shareText;
    private Object isProductSale;
    private Owner owner;
    private Discounts discounts;
    private Contractor contractor;
    private Object[] fields;
    private boolean purchaseAvailable;
    private Object[] productDiscounts;
    private String partnerLink;
    private String partnerLinkTitle;
    private String partnerLinkDescription;
    private boolean hasResponseToRequest;
    private boolean usePromoCreateAb;
    private Attribute[] attributes;
    private String supportURL;
    private Delivery[] delivery;
    private boolean paymentAvailable;
    private boolean deliveryAvailable;
    private boolean canBuy;
    private long deliveryType;
    private boolean canPromote;
    private String paidBoostButtonText;
    private Info info;
    private String priceDescription;
    private boolean deliveryBlock;
    private boolean deliveryVisible;
    private boolean deliveryEnabled;
    private DeliveryTexts deliveryTexts;
    private long salaryType;
    private Object masterID;
    private boolean isMasterExists;

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("linked_id")
    public Object getLinkedID() { return linkedID; }
    @JsonProperty("linked_id")
    public void setLinkedID(Object value) { this.linkedID = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("slug")
    public String getSlug() { return slug; }
    @JsonProperty("slug")
    public void setSlug(String value) { this.slug = value; }

    @JsonProperty("price")
    public long getPrice() { return price; }
    @JsonProperty("price")
    public void setPrice(long value) { this.price = value; }

    @JsonProperty("price_type")
    public Object getPriceType() { return priceType; }
    @JsonProperty("price_type")
    public void setPriceType(Object value) { this.priceType = value; }

    @JsonProperty("price_text")
    public String getPriceText() { return priceText; }
    @JsonProperty("price_text")
    public void setPriceText(String value) { this.priceText = value; }

    @JsonProperty("discount")
    public long getDiscount() { return discount; }
    @JsonProperty("discount")
    public void setDiscount(long value) { this.discount = value; }

    @JsonProperty("discounted_price")
    public long getDiscountedPrice() { return discountedPrice; }
    @JsonProperty("discounted_price")
    public void setDiscountedPrice(long value) { this.discountedPrice = value; }

    @JsonProperty("date_created")
    public long getDateCreated() { return dateCreated; }
    @JsonProperty("date_created")
    public void setDateCreated(long value) { this.dateCreated = value; }

    @JsonProperty("date_updated")
    public long getDateUpdated() { return dateUpdated; }
    @JsonProperty("date_updated")
    public void setDateUpdated(long value) { this.dateUpdated = value; }

    @JsonProperty("date_published")
    public long getDatePublished() { return datePublished; }
    @JsonProperty("date_published")
    public void setDatePublished(long value) { this.datePublished = value; }

    @JsonProperty("date_sold")
    public Object getDateSold() { return dateSold; }
    @JsonProperty("date_sold")
    public void setDateSold(Object value) { this.dateSold = value; }

    @JsonProperty("date_blocked")
    public Object getDateBlocked() { return dateBlocked; }
    @JsonProperty("date_blocked")
    public void setDateBlocked(Object value) { this.dateBlocked = value; }

    @JsonProperty("date_deleted")
    public Object getDateDeleted() { return dateDeleted; }
    @JsonProperty("date_deleted")
    public void setDateDeleted(Object value) { this.dateDeleted = value; }

    @JsonProperty("date_archivation")
    public long getDateArchivation() { return dateArchivation; }
    @JsonProperty("date_archivation")
    public void setDateArchivation(long value) { this.dateArchivation = value; }

    @JsonProperty("is_published")
    public boolean getIsPublished() { return isPublished; }
    @JsonProperty("is_published")
    public void setIsPublished(boolean value) { this.isPublished = value; }

    @JsonProperty("is_sold")
    public boolean getIsSold() { return isSold; }
    @JsonProperty("is_sold")
    public void setIsSold(boolean value) { this.isSold = value; }

    @JsonProperty("is_deleted")
    public boolean getIsDeleted() { return isDeleted; }
    @JsonProperty("is_deleted")
    public void setIsDeleted(boolean value) { this.isDeleted = value; }

    @JsonProperty("is_blocked")
    public boolean getIsBlocked() { return isBlocked; }
    @JsonProperty("is_blocked")
    public void setIsBlocked(boolean value) { this.isBlocked = value; }

    @JsonProperty("is_archived")
    public boolean getIsArchived() { return isArchived; }
    @JsonProperty("is_archived")
    public void setIsArchived(boolean value) { this.isArchived = value; }

    @JsonProperty("is_expiring")
    public boolean getIsExpiring() { return isExpiring; }
    @JsonProperty("is_expiring")
    public void setIsExpiring(boolean value) { this.isExpiring = value; }

    @JsonProperty("is_verified")
    public boolean getIsVerified() { return isVerified; }
    @JsonProperty("is_verified")
    public void setIsVerified(boolean value) { this.isVerified = value; }

    @JsonProperty("is_promoted")
    public boolean getIsPromoted() { return isPromoted; }
    @JsonProperty("is_promoted")
    public void setIsPromoted(boolean value) { this.isPromoted = value; }

    @JsonProperty("sold_mode")
    public long getSoldMode() { return soldMode; }
    @JsonProperty("sold_mode")
    public void setSoldMode(long value) { this.soldMode = value; }

    @JsonProperty("archive_mode")
    public long getArchiveMode() { return archiveMode; }
    @JsonProperty("archive_mode")
    public void setArchiveMode(long value) { this.archiveMode = value; }

    @JsonProperty("block_mode")
    public long getBlockMode() { return blockMode; }
    @JsonProperty("block_mode")
    public void setBlockMode(long value) { this.blockMode = value; }

    @JsonProperty("block_type")
    public long getBlockType() { return blockType; }
    @JsonProperty("block_type")
    public void setBlockType(long value) { this.blockType = value; }

    @JsonProperty("block_types")
    public Object[] getBlockTypes() { return blockTypes; }
    @JsonProperty("block_types")
    public void setBlockTypes(Object[] value) { this.blockTypes = value; }

    @JsonProperty("category")
    public long getCategory() { return category; }
    @JsonProperty("category")
    public void setCategory(long value) { this.category = value; }

    @JsonProperty("subcategory")
    public long getSubcategory() { return subcategory; }
    @JsonProperty("subcategory")
    public void setSubcategory(long value) { this.subcategory = value; }

    @JsonProperty("date_favorited")
    public Object getDateFavorited() { return dateFavorited; }
    @JsonProperty("date_favorited")
    public void setDateFavorited(Object value) { this.dateFavorited = value; }

    @JsonProperty("favorite_counter")
    public long getFavoriteCounter() { return favoriteCounter; }
    @JsonProperty("favorite_counter")
    public void setFavoriteCounter(long value) { this.favoriteCounter = value; }

    @JsonProperty("group_id")
    public long getGroupID() { return groupID; }
    @JsonProperty("group_id")
    public void setGroupID(long value) { this.groupID = value; }

    @JsonProperty("url")
    public String getURL() { return url; }
    @JsonProperty("url")
    public void setURL(String value) { this.url = value; }

    @JsonProperty("url_branch")
    public String getURLBranch() { return urlBranch; }
    @JsonProperty("url_branch")
    public void setURLBranch(String value) { this.urlBranch = value; }

    @JsonProperty("short_url")
    public String getShortURL() { return shortURL; }
    @JsonProperty("short_url")
    public void setShortURL(String value) { this.shortURL = value; }

    @JsonProperty("contacts_visible")
    public boolean getContactsVisible() { return contactsVisible; }
    @JsonProperty("contacts_visible")
    public void setContactsVisible(boolean value) { this.contactsVisible = value; }

    @JsonProperty("images")
    public ImageElement[] getImages() { return images; }
    @JsonProperty("images")
    public void setImages(ImageElement[] value) { this.images = value; }

    @JsonProperty("location")
    public DataLocation getLocation() { return location; }
    @JsonProperty("location")
    public void setLocation(DataLocation value) { this.location = value; }

    @JsonProperty("price_with_discount_seller")
    public long getPriceWithDiscountSeller() { return priceWithDiscountSeller; }
    @JsonProperty("price_with_discount_seller")
    public void setPriceWithDiscountSeller(long value) { this.priceWithDiscountSeller = value; }

    @JsonProperty("fire_promo_state")
    public long getFirePromoState() { return firePromoState; }
    @JsonProperty("fire_promo_state")
    public void setFirePromoState(long value) { this.firePromoState = value; }

    @JsonProperty("p2p_call_rating_needed")
    public boolean getP2PCallRatingNeeded() { return p2PCallRatingNeeded; }
    @JsonProperty("p2p_call_rating_needed")
    public void setP2PCallRatingNeeded(boolean value) { this.p2PCallRatingNeeded = value; }

    @JsonProperty("is_default_photo")
    public boolean getIsDefaultPhoto() { return isDefaultPhoto; }
    @JsonProperty("is_default_photo")
    public void setIsDefaultPhoto(boolean value) { this.isDefaultPhoto = value; }

    @JsonProperty("block_type_text")
    public String getBlockTypeText() { return blockTypeText; }
    @JsonProperty("block_type_text")
    public void setBlockTypeText(String value) { this.blockTypeText = value; }

    @JsonProperty("is_favorite")
    public boolean getIsFavorite() { return isFavorite; }
    @JsonProperty("is_favorite")
    public void setIsFavorite(boolean value) { this.isFavorite = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }

    @JsonProperty("views")
    public long getViews() { return views; }
    @JsonProperty("views")
    public void setViews(long value) { this.views = value; }

    @JsonProperty("share_url")
    public String getShareURL() { return shareURL; }
    @JsonProperty("share_url")
    public void setShareURL(String value) { this.shareURL = value; }

    @JsonProperty("share_text")
    public String getShareText() { return shareText; }
    @JsonProperty("share_text")
    public void setShareText(String value) { this.shareText = value; }

    @JsonProperty("is_product_sale")
    public Object getIsProductSale() { return isProductSale; }
    @JsonProperty("is_product_sale")
    public void setIsProductSale(Object value) { this.isProductSale = value; }

    @JsonProperty("owner")
    public Owner getOwner() { return owner; }
    @JsonProperty("owner")
    public void setOwner(Owner value) { this.owner = value; }

    @JsonProperty("discounts")
    public Discounts getDiscounts() { return discounts; }
    @JsonProperty("discounts")
    public void setDiscounts(Discounts value) { this.discounts = value; }

    @JsonProperty("contractor")
    public Contractor getContractor() { return contractor; }
    @JsonProperty("contractor")
    public void setContractor(Contractor value) { this.contractor = value; }

    @JsonProperty("fields")
    public Object[] getFields() { return fields; }
    @JsonProperty("fields")
    public void setFields(Object[] value) { this.fields = value; }

    @JsonProperty("purchase_available")
    public boolean getPurchaseAvailable() { return purchaseAvailable; }
    @JsonProperty("purchase_available")
    public void setPurchaseAvailable(boolean value) { this.purchaseAvailable = value; }

    @JsonProperty("product_discounts")
    public Object[] getProductDiscounts() { return productDiscounts; }
    @JsonProperty("product_discounts")
    public void setProductDiscounts(Object[] value) { this.productDiscounts = value; }

    @JsonProperty("partner_link")
    public String getPartnerLink() { return partnerLink; }
    @JsonProperty("partner_link")
    public void setPartnerLink(String value) { this.partnerLink = value; }

    @JsonProperty("partner_link_title")
    public String getPartnerLinkTitle() { return partnerLinkTitle; }
    @JsonProperty("partner_link_title")
    public void setPartnerLinkTitle(String value) { this.partnerLinkTitle = value; }

    @JsonProperty("partner_link_description")
    public String getPartnerLinkDescription() { return partnerLinkDescription; }
    @JsonProperty("partner_link_description")
    public void setPartnerLinkDescription(String value) { this.partnerLinkDescription = value; }

    @JsonProperty("has_response_to_request")
    public boolean getHasResponseToRequest() { return hasResponseToRequest; }
    @JsonProperty("has_response_to_request")
    public void setHasResponseToRequest(boolean value) { this.hasResponseToRequest = value; }

    @JsonProperty("use_promo_create_ab")
    public boolean getUsePromoCreateAb() { return usePromoCreateAb; }
    @JsonProperty("use_promo_create_ab")
    public void setUsePromoCreateAb(boolean value) { this.usePromoCreateAb = value; }

    @JsonProperty("attributes")
    public Attribute[] getAttributes() { return attributes; }
    @JsonProperty("attributes")
    public void setAttributes(Attribute[] value) { this.attributes = value; }

    @JsonProperty("support_url")
    public String getSupportURL() { return supportURL; }
    @JsonProperty("support_url")
    public void setSupportURL(String value) { this.supportURL = value; }

    @JsonProperty("delivery")
    public Delivery[] getDelivery() { return delivery; }
    @JsonProperty("delivery")
    public void setDelivery(Delivery[] value) { this.delivery = value; }

    @JsonProperty("payment_available")
    public boolean getPaymentAvailable() { return paymentAvailable; }
    @JsonProperty("payment_available")
    public void setPaymentAvailable(boolean value) { this.paymentAvailable = value; }

    @JsonProperty("delivery_available")
    public boolean getDeliveryAvailable() { return deliveryAvailable; }
    @JsonProperty("delivery_available")
    public void setDeliveryAvailable(boolean value) { this.deliveryAvailable = value; }

    @JsonProperty("can_buy")
    public boolean getCanBuy() { return canBuy; }
    @JsonProperty("can_buy")
    public void setCanBuy(boolean value) { this.canBuy = value; }

    @JsonProperty("delivery_type")
    public long getDeliveryType() { return deliveryType; }
    @JsonProperty("delivery_type")
    public void setDeliveryType(long value) { this.deliveryType = value; }

    @JsonProperty("can_promote")
    public boolean getCanPromote() { return canPromote; }
    @JsonProperty("can_promote")
    public void setCanPromote(boolean value) { this.canPromote = value; }

    @JsonProperty("paid_boost_button_text")
    public String getPaidBoostButtonText() { return paidBoostButtonText; }
    @JsonProperty("paid_boost_button_text")
    public void setPaidBoostButtonText(String value) { this.paidBoostButtonText = value; }

    @JsonProperty("info")
    public Info getInfo() { return info; }
    @JsonProperty("info")
    public void setInfo(Info value) { this.info = value; }

    @JsonProperty("price_description")
    public String getPriceDescription() { return priceDescription; }
    @JsonProperty("price_description")
    public void setPriceDescription(String value) { this.priceDescription = value; }

    @JsonProperty("delivery_block")
    public boolean getDeliveryBlock() { return deliveryBlock; }
    @JsonProperty("delivery_block")
    public void setDeliveryBlock(boolean value) { this.deliveryBlock = value; }

    @JsonProperty("delivery_visible")
    public boolean getDeliveryVisible() { return deliveryVisible; }
    @JsonProperty("delivery_visible")
    public void setDeliveryVisible(boolean value) { this.deliveryVisible = value; }

    @JsonProperty("delivery_enabled")
    public boolean getDeliveryEnabled() { return deliveryEnabled; }
    @JsonProperty("delivery_enabled")
    public void setDeliveryEnabled(boolean value) { this.deliveryEnabled = value; }

    @JsonProperty("delivery_texts")
    public DeliveryTexts getDeliveryTexts() { return deliveryTexts; }
    @JsonProperty("delivery_texts")
    public void setDeliveryTexts(DeliveryTexts value) { this.deliveryTexts = value; }

    @JsonProperty("salary_type")
    public long getSalaryType() { return salaryType; }
    @JsonProperty("salary_type")
    public void setSalaryType(long value) { this.salaryType = value; }

    @JsonProperty("master_id")
    public Object getMasterID() { return masterID; }
    @JsonProperty("master_id")
    public void setMasterID(Object value) { this.masterID = value; }

    @JsonProperty("is_master_exists")
    public boolean getIsMasterExists() { return isMasterExists; }
    @JsonProperty("is_master_exists")
    public void setIsMasterExists(boolean value) { this.isMasterExists = value; }
}
