package tn.esprit.fundme.config;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
 
import tn.esprit.fundme.batch.ProductFieldSetMapper;
import tn.esprit.fundme.batch.ProductProcessor;
import tn.esprit.fundme.batch.ProductWriter;
import tn.esprit.fundme.entities.Product;
import tn.esprit.fundme.services.SupplierService;



@Configuration
@EnableBatchProcessing

public class BatchConfig {

    private static final String FILE_NAME = "products.csv";
    private static final String JOB_NAME = "listProductsJob";
    private static final String STEP_NAME = "processingStep";
    private static final String READER_NAME = "ProductItemReader";

    @Value("${header.names}")
    private String names;

    @Value("${line.delimiter}")
    private String delimiter;

    @Autowired
	SupplierService supplierService;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step productStep() {
        return stepBuilderFactory.get(STEP_NAME)
                .<Product, Product>chunk(5)
                .reader(ProductItemReader())
                .processor(ProductItemProcessor())
                .writer(ProductItemWriter())
                .build();
    }

    @Bean
    @Qualifier("prod")
    public Job listProductsJob(Step step1) {
        return jobBuilderFactory.get(JOB_NAME)
                .start(step1)
                .build();
    }

    @Bean
    public ItemReader<Product> ProductItemReader() {
        FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(FILE_NAME));
        reader.setName(READER_NAME);
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        
        return reader;

    }

    @Bean
    public LineMapper<Product> lineMapper() {

        final DefaultLineMapper<Product> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(delimiter);
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(names.split(delimiter));
        
        final ProductFieldSetMapper fieldSetMapper = new ProductFieldSetMapper(supplierService.retrieveAllSupplier());
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    @Bean
    public ItemProcessor<Product, Product> ProductItemProcessor() {
        return new ProductProcessor();
    }

    @Bean
    public ItemWriter<Product> ProductItemWriter() {
        return new ProductWriter();
    }
}
