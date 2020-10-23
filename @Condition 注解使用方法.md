---
title: spring @Condition
date: 2020-10-23 15:34:11
tags: ["Spring,注解,Condition"]
---
## @Condition 作用
@Condition 可作用于方法或者类上，用于是否注册组件对象。  
理解: 就是添加了这个注解，spring 会解析这个注解，然后判断当前注册的bean是否应该注册。  
或者当前类是否应该注册!

## @Condition 分析
<!--more-->
``` 
/**
 * Indicates that a component is only eligible for registration when all
 * {@linkplain #value specified conditions} match.
 *
 * <p>A <em>condition</em> is any state that can be determined programmatically
 * before the bean definition is due to be registered (see {@link Condition} for details).
 *
 * <p>The {@code @Conditional} annotation may be used in any of the following ways:
 * <ul>
 * <li>as a type-level annotation on any class directly or indirectly annotated with
 * {@code @Component}, including {@link Configuration @Configuration} classes</li>
 * <li>as a meta-annotation, for the purpose of composing custom stereotype
 * annotations</li>
 * <li>as a method-level annotation on any {@link Bean @Bean} method</li>
 * </ul>
 *
 * <p>If a {@code @Configuration} class is marked with {@code @Conditional},
 * all of the {@code @Bean} methods, {@link Import @Import} annotations, and
 * {@link ComponentScan @ComponentScan} annotations associated with that
 * class will be subject to the conditions.
 *
 * <p><strong>NOTE</strong>: Inheritance of {@code @Conditional} annotations
 * is not supported; any conditions from superclasses or from overridden
 * methods will not be considered. In order to enforce these semantics,
 * {@code @Conditional} itself is not declared as
 * {@link java.lang.annotation.Inherited @Inherited}; furthermore, any
 * custom <em>composed annotation</em> that is meta-annotated with
 * {@code @Conditional} must not be declared as {@code @Inherited}.
 *
 * @author Phillip Webb
 * @author Sam Brannen
 * @since 4.0
 * @see Condition
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Conditional {

	/**
	 * All {@link Condition}s that must {@linkplain Condition#matches match}
	 * in order for the component to be registered.
	 */
	Class<? extends Condition>[] value();

}
```  
这里可能要涉及到一些注解相关的知识:  
ElementType.TYPE 这里可以点进去看一下,官方的解释很好理解,:  
可作用于 类，接口，包括注解(可用作混合注解),或者 枚举声明  
/** Class, interface (including annotation type), or enum declaration */  
ElementType.METHOD : 作用于方法声明  
按照 @Condition 来分析参数默认只能填入一个 Condition 数组,尝试去看一下 Condition，发现是一个接口,如下:  
``` 
/**
	 * Determine if the condition matches.
	 * @param context the condition context
	 * @param metadata metadata of the {@link org.springframework.core.type.AnnotationMetadata class}
	 * or {@link org.springframework.core.type.MethodMetadata method} being checked
	 * @return {@code true} if the condition matches and the component can be registered,
	 * or {@code false} to veto the annotated component's registration
	 */
	boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata);
```
## 如何使用 @Condition ？
如下代码:  
```
/**
 * @author cayden
 * @version V1.0
 * @date 2020/10/22 14:10
 */
public class ConditionOwn implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 上下文需要的 context
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 运行环境
        Environment environment = context.getEnvironment();
        if (environment.getProperty("os.name").contains("Windows")){
            return true;
        }

        return false;
    }
}
```  
核心其实就是我们要重写 Condition的 matches方法，然后返回是否需要加载就行了，这里所拿道德参数很关键，
metadata是源注解，是当前类的注解，你可以解析匹配某个注解返回  
environment: environment 是运行环境，具体可研究代码  

## 注解到类上面
``` 
/**
 * @author cayden
 * @date 2020/3/16 15:09
 * 注意，如果实现了BeanFactoryPostProcessor 或者他的子类接口 BeanDefinitionRegistryPostProcessor
 * 在实现 BeanPostProcessor 的时候，是不能取到当前类的
 */
@Component
@Conditional(value = ConditionOwn.class)
@Order(value = 1)
public class Dao implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 实现 beanFactoryPostProcessor 可以拿到bean的定义然后干预 spring bean 的生命周期
        BeanDefinition dao = beanFactory.getBeanDefinition("nothing");
        System.out.println(dao.isSingleton());
    }

    public void print(){
        System.out.println("i'm dao !");
    }
}
```

## 注解在方法上
``` 
@Bean
    @Conditional(ConditionOwn.class)
    public TestBean createTestBean(){
        return new TestBean();
    }
```

## spring 是如何解析 @Condition 注解的，在什么时候解析的?
``` 
/**
	 * Determine if an item should be skipped based on {@code @Conditional} annotations.
	 * @param metadata the meta data
	 * @param phase the phase of the call
	 * @return if the item should be skipped
	 */
	public boolean shouldSkip(@Nullable AnnotatedTypeMetadata metadata, @Nullable ConfigurationPhase phase) {
		if (metadata == null || !metadata.isAnnotated(Conditional.class.getName())) {
			return false;
		}

		if (phase == null) {
			if (metadata instanceof AnnotationMetadata &&
					ConfigurationClassUtils.isConfigurationCandidate((AnnotationMetadata) metadata)) {
				return shouldSkip(metadata, ConfigurationPhase.PARSE_CONFIGURATION);
			}
			return shouldSkip(metadata, ConfigurationPhase.REGISTER_BEAN);
		}

		List<Condition> conditions = new ArrayList<>();
		for (String[] conditionClasses : getConditionClasses(metadata)) {
			for (String conditionClass : conditionClasses) {
				Condition condition = getCondition(conditionClass, this.context.getClassLoader());
				conditions.add(condition);
			}
		}
```


  
