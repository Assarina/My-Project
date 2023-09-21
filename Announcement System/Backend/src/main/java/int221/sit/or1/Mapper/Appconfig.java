package int221.sit.or1.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconfig {
    @Bean
    public ModelMapper modelMapper(){ return  new ModelMapper();}
    @Bean
    public  ListMapper listMapper(){
        return ListMapper.getInstance();
    }
}
