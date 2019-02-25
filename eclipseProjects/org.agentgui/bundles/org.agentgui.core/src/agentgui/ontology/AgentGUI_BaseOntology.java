// file: AgentGUI_BaseOntology.java generated by ontology bean generator.  DO NOT EDIT, UNLESS YOU ARE REALLY SURE WHAT YOU ARE DOING!
package agentgui.ontology;

import jade.content.onto.*;
import jade.content.schema.*;
import jade.util.leap.HashMap;
import jade.content.lang.Codec;
import jade.core.CaseInsensitiveString;

/** file: AgentGUI_BaseOntology.java
 * @author ontology bean generator
 * @version 2019/02/25, 13:33:15
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
    public static final String SIMPLE_INTEGER_INTEGERVALUE="IntegerValue";
    public static final String SIMPLE_INTEGER="Simple_Integer";
    public static final String XYCHART_XYSERIESVISUALISATIONSETTINGS="xySeriesVisualisationSettings";
    public static final String XYCHART_XYCHARTDATA="xyChartData";
    public static final String XYCHART="XyChart";
    public static final String TIMESERIES_TIMESERIESVALUEPAIRS="timeSeriesValuePairs";
    public static final String TIMESERIES="TimeSeries";
    public static final String XYDATASERIES_XYVALUEPAIRS="xyValuePairs";
    public static final String XYDATASERIES_AVOIDDUPLICATEXVALUES="avoidDuplicateXValues";
    public static final String XYDATASERIES_AUTOSORT="autoSort";
    public static final String XYDATASERIES="XyDataSeries";
    public static final String VALUEPAIR="ValuePair";
    public static final String SIMPLE_FLOAT_FLOATVALUE="FloatValue";
    public static final String SIMPLE_FLOAT="Simple_Float";
    public static final String TIMESERIESCHARTSETTINGS_TIMEFORMAT="timeFormat";
    public static final String TIMESERIESCHARTSETTINGS="TimeSeriesChartSettings";
    public static final String FORMULA_FORMULA="formula";
    public static final String FORMULA="Formula";
    public static final String SIMPLE_STRING_STRINGVALUE="StringValue";
    public static final String SIMPLE_STRING="Simple_String";
    public static final String SIMPLE_LONG_STRINGLONGVALUE="StringLongValue";
    public static final String SIMPLE_LONG="Simple_Long";
    public static final String VISUALIZATIONSETTINGS="VisualizationSettings";
    public static final String SIMPLE_OBJECT_OBJECTVALUE="ObjectValue";
    public static final String SIMPLE_OBJECT="Simple_Object";
    public static final String XYSERIESCHARTSETTINGS="XySeriesChartSettings";
    public static final String CHARTSETTINGSGENERAL_YAXISCOLORS="yAxisColors";
    public static final String CHARTSETTINGSGENERAL_XAXISLABEL="xAxisLabel";
    public static final String CHARTSETTINGSGENERAL_CHARTTITLE="chartTitle";
    public static final String CHARTSETTINGSGENERAL_RENDERERTYPE="rendererType";
    public static final String CHARTSETTINGSGENERAL_YAXISLINEWIDTH="yAxisLineWidth";
    public static final String CHARTSETTINGSGENERAL_YAXISLABEL="yAxisLabel";
    public static final String CHARTSETTINGSGENERAL="ChartSettingsGeneral";
    public static final String TIMESERIESVALUEPAIR_TIMESTAMP="timestamp";
    public static final String TIMESERIESVALUEPAIR_VALUE="value";
    public static final String TIMESERIESVALUEPAIR="TimeSeriesValuePair";
    public static final String SIMPLE_BOOLEAN_BOOLEANVALUE="BooleanValue";
    public static final String SIMPLE_BOOLEAN="Simple_Boolean";
    public static final String TIMESERIESCHART_REALTIME="realTime";
    public static final String TIMESERIESCHART_TIMESERIESCHARTDATA="timeSeriesChartData";
    public static final String TIMESERIESCHART_TIMESERIESVISUALISATIONSETTINGS="timeSeriesVisualisationSettings";
    public static final String TIMESERIESCHART="TimeSeriesChart";
    public static final String DATASERIES_UNIT="unit";
    public static final String DATASERIES_LABEL="label";
    public static final String DATASERIES="DataSeries";
    public static final String CHART="Chart";
    public static final String XYVALUEPAIR_YVALUE="yValue";
    public static final String XYVALUEPAIR_XVALUE="xValue";
    public static final String XYVALUEPAIR="XyValuePair";

  /**
   * Constructor
  */
  private AgentGUI_BaseOntology(){ 
    super(ONTOLOGY_NAME, BasicOntology.getInstance());
    try { 

    // adding Concept(s)
    ConceptSchema xyValuePairSchema = new ConceptSchema(XYVALUEPAIR);
    add(xyValuePairSchema, agentgui.ontology.XyValuePair.class);
    ConceptSchema chartSchema = new ConceptSchema(CHART);
    add(chartSchema, agentgui.ontology.Chart.class);
    ConceptSchema dataSeriesSchema = new ConceptSchema(DATASERIES);
    add(dataSeriesSchema, agentgui.ontology.DataSeries.class);
    ConceptSchema timeSeriesChartSchema = new ConceptSchema(TIMESERIESCHART);
    add(timeSeriesChartSchema, agentgui.ontology.TimeSeriesChart.class);
    ConceptSchema simple_BooleanSchema = new ConceptSchema(SIMPLE_BOOLEAN);
    add(simple_BooleanSchema, agentgui.ontology.Simple_Boolean.class);
    ConceptSchema timeSeriesValuePairSchema = new ConceptSchema(TIMESERIESVALUEPAIR);
    add(timeSeriesValuePairSchema, agentgui.ontology.TimeSeriesValuePair.class);
    ConceptSchema chartSettingsGeneralSchema = new ConceptSchema(CHARTSETTINGSGENERAL);
    add(chartSettingsGeneralSchema, agentgui.ontology.ChartSettingsGeneral.class);
    ConceptSchema xySeriesChartSettingsSchema = new ConceptSchema(XYSERIESCHARTSETTINGS);
    add(xySeriesChartSettingsSchema, agentgui.ontology.XySeriesChartSettings.class);
    ConceptSchema simple_ObjectSchema = new ConceptSchema(SIMPLE_OBJECT);
    add(simple_ObjectSchema, agentgui.ontology.Simple_Object.class);
    ConceptSchema visualizationSettingsSchema = new ConceptSchema(VISUALIZATIONSETTINGS);
    add(visualizationSettingsSchema, agentgui.ontology.VisualizationSettings.class);
    ConceptSchema simple_LongSchema = new ConceptSchema(SIMPLE_LONG);
    add(simple_LongSchema, agentgui.ontology.Simple_Long.class);
    ConceptSchema simple_StringSchema = new ConceptSchema(SIMPLE_STRING);
    add(simple_StringSchema, agentgui.ontology.Simple_String.class);
    ConceptSchema formulaSchema = new ConceptSchema(FORMULA);
    add(formulaSchema, agentgui.ontology.Formula.class);
    ConceptSchema timeSeriesChartSettingsSchema = new ConceptSchema(TIMESERIESCHARTSETTINGS);
    add(timeSeriesChartSettingsSchema, agentgui.ontology.TimeSeriesChartSettings.class);
    ConceptSchema simple_FloatSchema = new ConceptSchema(SIMPLE_FLOAT);
    add(simple_FloatSchema, agentgui.ontology.Simple_Float.class);
    ConceptSchema valuePairSchema = new ConceptSchema(VALUEPAIR);
    add(valuePairSchema, agentgui.ontology.ValuePair.class);
    ConceptSchema xyDataSeriesSchema = new ConceptSchema(XYDATASERIES);
    add(xyDataSeriesSchema, agentgui.ontology.XyDataSeries.class);
    ConceptSchema timeSeriesSchema = new ConceptSchema(TIMESERIES);
    add(timeSeriesSchema, agentgui.ontology.TimeSeries.class);
    ConceptSchema xyChartSchema = new ConceptSchema(XYCHART);
    add(xyChartSchema, agentgui.ontology.XyChart.class);
    ConceptSchema simple_IntegerSchema = new ConceptSchema(SIMPLE_INTEGER);
    add(simple_IntegerSchema, agentgui.ontology.Simple_Integer.class);

    // adding AgentAction(s)

    // adding AID(s)

    // adding Predicate(s)


    // adding fields
    xyValuePairSchema.add(XYVALUEPAIR_XVALUE, simple_FloatSchema, ObjectSchema.OPTIONAL);
    xyValuePairSchema.add(XYVALUEPAIR_YVALUE, simple_FloatSchema, ObjectSchema.OPTIONAL);
    dataSeriesSchema.add(DATASERIES_LABEL, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    dataSeriesSchema.add(DATASERIES_UNIT, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    timeSeriesChartSchema.add(TIMESERIESCHART_TIMESERIESVISUALISATIONSETTINGS, timeSeriesChartSettingsSchema, ObjectSchema.OPTIONAL);
    timeSeriesChartSchema.add(TIMESERIESCHART_TIMESERIESCHARTDATA, timeSeriesSchema, 0, ObjectSchema.UNLIMITED);
    timeSeriesChartSchema.add(TIMESERIESCHART_REALTIME, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);
    simple_BooleanSchema.add(SIMPLE_BOOLEAN_BOOLEANVALUE, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);
    timeSeriesValuePairSchema.add(TIMESERIESVALUEPAIR_VALUE, simple_FloatSchema, ObjectSchema.MANDATORY);
    timeSeriesValuePairSchema.add(TIMESERIESVALUEPAIR_TIMESTAMP, simple_LongSchema, ObjectSchema.MANDATORY);
    chartSettingsGeneralSchema.add(CHARTSETTINGSGENERAL_YAXISLABEL, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    chartSettingsGeneralSchema.add(CHARTSETTINGSGENERAL_YAXISLINEWIDTH, (TermSchema)getSchema(BasicOntology.FLOAT), 0, ObjectSchema.UNLIMITED);
    chartSettingsGeneralSchema.add(CHARTSETTINGSGENERAL_RENDERERTYPE, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    chartSettingsGeneralSchema.add(CHARTSETTINGSGENERAL_CHARTTITLE, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    chartSettingsGeneralSchema.add(CHARTSETTINGSGENERAL_XAXISLABEL, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    chartSettingsGeneralSchema.add(CHARTSETTINGSGENERAL_YAXISCOLORS, (TermSchema)getSchema(BasicOntology.STRING), 0, ObjectSchema.UNLIMITED);
    simple_ObjectSchema.add(SIMPLE_OBJECT_OBJECTVALUE, new ConceptSchema("Concept"), ObjectSchema.OPTIONAL);
    simple_LongSchema.add(SIMPLE_LONG_STRINGLONGVALUE, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    simple_StringSchema.add(SIMPLE_STRING_STRINGVALUE, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    formulaSchema.add(FORMULA_FORMULA, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    timeSeriesChartSettingsSchema.add(TIMESERIESCHARTSETTINGS_TIMEFORMAT, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    simple_FloatSchema.add(SIMPLE_FLOAT_FLOATVALUE, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    xyDataSeriesSchema.add(XYDATASERIES_AUTOSORT, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);
    xyDataSeriesSchema.add(XYDATASERIES_AVOIDDUPLICATEXVALUES, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);
    xyDataSeriesSchema.add(XYDATASERIES_XYVALUEPAIRS, xyValuePairSchema, 0, ObjectSchema.UNLIMITED);
    timeSeriesSchema.add(TIMESERIES_TIMESERIESVALUEPAIRS, timeSeriesValuePairSchema, 0, ObjectSchema.UNLIMITED);
    xyChartSchema.add(XYCHART_XYCHARTDATA, xyDataSeriesSchema, 0, ObjectSchema.UNLIMITED);
    xyChartSchema.add(XYCHART_XYSERIESVISUALISATIONSETTINGS, xySeriesChartSettingsSchema, ObjectSchema.OPTIONAL);
    simple_IntegerSchema.add(SIMPLE_INTEGER_INTEGERVALUE, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);

    // adding name mappings

    // adding inheritance
    xyValuePairSchema.addSuperSchema(valuePairSchema);
    timeSeriesChartSchema.addSuperSchema(chartSchema);
    timeSeriesValuePairSchema.addSuperSchema(valuePairSchema);
    chartSettingsGeneralSchema.addSuperSchema(visualizationSettingsSchema);
    xySeriesChartSettingsSchema.addSuperSchema(chartSettingsGeneralSchema);
    timeSeriesChartSettingsSchema.addSuperSchema(chartSettingsGeneralSchema);
    xyDataSeriesSchema.addSuperSchema(dataSeriesSchema);
    timeSeriesSchema.addSuperSchema(dataSeriesSchema);
    xyChartSchema.addSuperSchema(chartSchema);

   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
  }
