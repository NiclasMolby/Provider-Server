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

/**
 * Informations about a product
 */
@ApiModel(description = "Informations about a product")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-23T10:30:12.404Z")
public class Product   {
  @JsonProperty("productName")
  private String productName = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("price")
  private String price = null;

  @JsonProperty("packaging")
  private String packaging = null;

  public Product productName(String productName) {
    this.productName = productName;
    return this;
  }

   /**
   * The name of the product
   * @return productName
  **/
  @ApiModelProperty(value = "The name of the product")
  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Product description(String description) {
    this.description = description;
    return this;
  }

   /**
   * The description about the product
   * @return description
  **/
  @ApiModelProperty(value = "The description about the product")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Product price(String price) {
    this.price = price;
    return this;
  }

   /**
   * The price of the product
   * @return price
  **/
  @ApiModelProperty(value = "The price of the product")
  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Product packaging(String packaging) {
    this.packaging = packaging;
    return this;
  }

   /**
   * The packaging of the product
   * @return packaging
  **/
  @ApiModelProperty(value = "The packaging of the product")
  public String getPackaging() {
    return packaging;
  }

  public void setPackaging(String packaging) {
    this.packaging = packaging;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(this.productName, product.productName) &&
        Objects.equals(this.description, product.description) &&
        Objects.equals(this.price, product.price) &&
        Objects.equals(this.packaging, product.packaging);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productName, description, price, packaging);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    packaging: ").append(toIndentedString(packaging)).append("\n");
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

