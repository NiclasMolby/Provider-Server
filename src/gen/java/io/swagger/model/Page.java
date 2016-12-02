/*
 * Provider server
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Note;
import io.swagger.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Page containing information about a supplier
 */
@ApiModel(description = "Page containing information about a supplier")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-02T12:21:14.070Z")
public class Page   {
  @JsonProperty("owner")
  private String owner = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("contactInformation")
  private String contactInformation = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("products")
  private List<Product> products = new ArrayList<Product>();

  @JsonProperty("note")
  private Note note = null;

  public Page owner(String owner) {
    this.owner = owner;
    return this;
  }

   /**
   * The owner of the page => The supplier
   * @return owner
  **/
  @ApiModelProperty(value = "The owner of the page => The supplier")
  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public Page description(String description) {
    this.description = description;
    return this;
  }

   /**
   * The description of the page owner
   * @return description
  **/
  @ApiModelProperty(value = "The description of the page owner")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Page contactInformation(String contactInformation) {
    this.contactInformation = contactInformation;
    return this;
  }

   /**
   * The contactinformation of the page owner
   * @return contactInformation
  **/
  @ApiModelProperty(value = "The contactinformation of the page owner")
  public String getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(String contactInformation) {
    this.contactInformation = contactInformation;
  }

  public Page location(String location) {
    this.location = location;
    return this;
  }

   /**
   * The location of the page owner
   * @return location
  **/
  @ApiModelProperty(value = "The location of the page owner")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Page products(List<Product> products) {
    this.products = products;
    return this;
  }

  public Page addProductsItem(Product productsItem) {
    this.products.add(productsItem);
    return this;
  }

   /**
   * The suppliers products
   * @return products
  **/
  @ApiModelProperty(value = "The suppliers products")
  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public Page note(Note note) {
    this.note = note;
    return this;
  }

   /**
   * Get note
   * @return note
  **/
  @ApiModelProperty(value = "")
  public Note getNote() {
    return note;
  }

  public void setNote(Note note) {
    this.note = note;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Page page = (Page) o;
    return Objects.equals(this.owner, page.owner) &&
        Objects.equals(this.description, page.description) &&
        Objects.equals(this.contactInformation, page.contactInformation) &&
        Objects.equals(this.location, page.location) &&
        Objects.equals(this.products, page.products) &&
        Objects.equals(this.note, page.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(owner, description, contactInformation, location, products, note);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Page {\n");
    
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    contactInformation: ").append(toIndentedString(contactInformation)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    products: ").append(toIndentedString(products)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

