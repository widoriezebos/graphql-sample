package net.riezebos.graphql.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import net.riezebos.graphql.generated.types.Brand;
import net.riezebos.graphql.generated.types.Model;

public class MockDataSource {

  private List<Brand> brands = new ArrayList<Brand>();
  private Map<String, Brand> brandsById = new HashMap<String, Brand>();
  private List<Model> models = new ArrayList<Model>();

  @PostConstruct
  private void init() {
    brands.add(Brand.newBuilder().id("1001").name("Mazda").vehicleType("Hatchback").build());
    brands.add(Brand.newBuilder().id("1002").name("Volvo").vehicleType("Station").build());
    brands.add(Brand.newBuilder().id("1003").name("Volkswagen").vehicleType("Hatchback").build());
    brands.add(Brand.newBuilder().id("1004").name("Seat").vehicleType("Sedan").build());

    brands.stream().forEach(br -> brandsById.put(br.getId(), br));

    int id = 101;

    Brand mazdaBrand = getBrandById("1001");
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Mazda 2").brand(mazdaBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Mazda 3").brand(mazdaBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Mazda CX-3").brand(mazdaBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Mazda CX-5").brand(mazdaBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Mzda6").brand(mazdaBrand).build());

    Brand volvoBrand = getBrandById("1002");
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("V40").brand(volvoBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("V50").brand(volvoBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("V60").brand(volvoBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("XC60").brand(volvoBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("V70").brand(volvoBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("V90").brand(volvoBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("XC90").brand(volvoBrand).build());

    Brand volkswagenBrand = getBrandById("1003");
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Polo").brand(volkswagenBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Golf").brand(volkswagenBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Tiguan").brand(volkswagenBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Touran").brand(volkswagenBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("T-Roc").brand(volkswagenBrand).build());

    Brand seatBrand = getBrandById("1004");
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Leon").brand(seatBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Ibiza").brand(seatBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Ateca").brand(seatBrand).build());
    models.add(Model.newBuilder().id(String.valueOf(id++)).name("Arona").brand(seatBrand).build());
  }

  public List<Brand> getBrands() {
    return brands;
  }

  public Brand getBrandById(String id) {
    return brandsById.get(id);
  }

  public List<Model> getModels() {
    return models;
  }

  public List<Model> getModels(String brandId) {
    return models.stream().filter(m -> m.getBrand().getId().equals(brandId)).collect(Collectors.toList());
  }
}
