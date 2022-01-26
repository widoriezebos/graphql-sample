package net.riezebos.graphql.resolvers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import graphql.schema.DataFetchingEnvironment;
import net.riezebos.graphql.generated.DgsConstants;
import net.riezebos.graphql.generated.types.Brand;
import net.riezebos.graphql.generated.types.Model;
import net.riezebos.graphql.repository.MockDataSource;

@DgsComponent
public class ModelDataResolver {

  @Autowired
  private MockDataSource repos;

  @DgsQuery
  public List<Model> models(@InputArgument(name = "name") Optional<String> name) {

    if (name.isEmpty()) {
      return repos.getModels();
    }

    return repos.getModels().stream().filter(model -> StringUtils.containsIgnoreCase(model.getName(), name.get())).collect(Collectors.toList());
  }
  
  @DgsData(parentType = DgsConstants.BRAND.TYPE_NAME, field = DgsConstants.BRAND.Models)
  public List<Model> brandModels(DataFetchingEnvironment dfe) {

    Brand brand = dfe.getSource();
    String brandId = brand.getId();
    
    return repos.getModels(brandId);
  }

}
