package tn.esprit.fundme.configbatch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.FileSystemResource;


import tn.esprit.fundme.entities.Deposit;


@Configuration
@EnableBatchProcessing
public class Springbatchconfig{
		
   @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Deposit> itemReader,
                  ItemProcessor<Deposit,Deposit> itemProcessor,
                   ItemWriter<Deposit> itemWriter
    ) {

        Step step = stepBuilderFactory.get("ETL-file-load")
              .<Deposit, Deposit>chunk(100)
               .reader(itemReader)
               .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return (Job) jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
   
   
   public ConversionService createConversionService() {
       DefaultConversionService conversionService = new DefaultConversionService();
       DefaultConversionService.addDefaultConverters(conversionService);
       conversionService.addConverter(new Converter<String, LocalDate>() {
           
           public LocalDate convert(String text) {
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
               return LocalDate.parse(text, formatter);
           }
       });
       return conversionService;
   }
   
    @Bean
    public FlatFileItemReader<Deposit> itemReader() {

        FlatFileItemReader<Deposit> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/Deposit.csv"));
        flatFileItemReader.setName("CSV-Reader");
       flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper2());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<Deposit> lineMapper2() {

        DefaultLineMapper<Deposit> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("rib", "balance", "dateCreationdeposit");

        BeanWrapperFieldSetMapper<Deposit> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Deposit.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}