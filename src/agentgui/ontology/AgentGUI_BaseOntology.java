// file: AgentGUI_BaseOntology.java generated by ontology bean generator.  DO NOT EDIT, UNLESS YOU ARE REALLY SURE WHAT YOU ARE DOING!
package agentgui.ontology;

import jade.content.onto.*;
import jade.content.schema.*;
import jade.util.leap.HashMap;
import jade.content.lang.Codec;
import jade.core.CaseInsensitiveString;

/** file: AgentGUI_BaseOntology.java
 * @author ontology bean generator
 * @version 2011/06/28, 17:23:30
 */
public class AgentGUI_BaseOntology extends jade.content.onto.Ontology  {
  //NAME
  public static final String ONTOLOGY_NAME = "AgentGUI_Base";
  // The singleton instance of this ontology
  private static ReflectiveIntrospector introspect = new ReflectiveIntrospector();
  private static Ontology theInstance = new AgentGUI_BaseOntology();
  public static Ontology getInstance() {
     return theInstance;
  }


   // VOCABULARY
    public static final String SIMPLE_OBJECT_OBJECTVALUE="ObjectValue";
    public static final String SIMPLE_OBJECT="Simple_Object";
    public static final String FORMULA_FORMULA="formula";
    public static final String FORMULA="Formula";
    public static final String SIMPLE_BOOLEAN_BOOLEANVALUE="BooleanValue";
    public static final String SIMPLE_BOOLEAN="Simple_Boolean";
    public static final String SIMPLE_INTEGER_INTEGERVALUE="IntegerValue";
    public static final String SIMPLE_INTEGER="Simple_Integer";
    public static final String SIMPLE_FLOAT_FLOATVALUE="FloatValue";
    public static final String SIMPLE_FLOAT="Simple_Float";
    public static final String SIMPLE_STRING_STRINGVALUE="StringValue";
    public static final String SIMPLE_STRING="Simple_String";

  /**
   * Constructor
  */
  private AgentGUI_BaseOntology(){ 
    super(ONTOLOGY_NAME, BasicOntology.getInstance());
    try { 

    // adding Concept(s)
    ConceptSchema simple_StringSchema = new ConceptSchema(SIMPLE_STRING);
    add(simple_StringSchema, agentgui.ontology.Simple_String.class);
    ConceptSchema simple_FloatSchema = new ConceptSchema(SIMPLE_FLOAT);
    add(simple_FloatSchema, agentgui.ontology.Simple_Float.class);
    ConceptSchema simple_IntegerSchema = new ConceptSchema(SIMPLE_INTEGER);
    add(simple_IntegerSchema, agentgui.ontology.Simple_Integer.class);
    ConceptSchema simple_BooleanSchema = new ConceptSchema(SIMPLE_BOOLEAN);
    add(simple_BooleanSchema, agentgui.ontology.Simple_Boolean.class);
    ConceptSchema formulaSchema = new ConceptSchema(FORMULA);
    add(formulaSchema, agentgui.ontology.Formula.class);
    ConceptSchema simple_ObjectSchema = new ConceptSchema(SIMPLE_OBJECT);
    add(simple_ObjectSchema, agentgui.ontology.Simple_Object.class);

    // adding AgentAction(s)

    // adding AID(s)

    // adding Predicate(s)


    // adding fields
    simple_StringSchema.add(SIMPLE_STRING_STRINGVALUE, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    simple_FloatSchema.add(SIMPLE_FLOAT_FLOATVALUE, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    simple_IntegerSchema.add(SIMPLE_INTEGER_INTEGERVALUE, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    simple_BooleanSchema.add(SIMPLE_BOOLEAN_BOOLEANVALUE, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);
    formulaSchema.add(FORMULA_FORMULA, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    simple_ObjectSchema.add(SIMPLE_OBJECT_OBJECTVALUE, new ConceptSchema("Concept"), ObjectSchema.OPTIONAL);

    // adding name mappings

    // adding inheritance

   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
  }
