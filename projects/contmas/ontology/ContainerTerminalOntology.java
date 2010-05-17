// file: ContainerTerminalOntology.java generated by ontology bean generator.  DO NOT EDIT, UNLESS YOU ARE REALLY SURE WHAT YOU ARE DOING!
package contmas.ontology;

import jade.content.onto.*;
import jade.content.schema.*;
import jade.util.leap.HashMap;
import jade.content.lang.Codec;
import jade.core.CaseInsensitiveString;

/** file: ContainerTerminalOntology.java
 * @author ontology bean generator
 * @version 2010/05/17, 22:08:19
 */
public class ContainerTerminalOntology extends jade.content.onto.Ontology  {
  //NAME
  public static final String ONTOLOGY_NAME = "ContainerTerminal";
  // The singleton instance of this ontology
  private static ReflectiveIntrospector introspect = new ReflectiveIntrospector();
  private static Ontology theInstance = new ContainerTerminalOntology();
  public static Ontology getInstance() {
     return theInstance;
  }


   // VOCABULARY
    public static final String STRADDLECARRIER="StraddleCarrier";
    public static final String CONTAINERHOLDER_CONTRACTORS="contractors";
    public static final String CONTAINERHOLDER_IS_IN_POSITION2="is_in_position2";
    public static final String CONTAINERHOLDER_LOCALNAME="localName";
    public static final String CONTAINERHOLDER_LIVES_IN="lives_in";
    public static final String CONTAINERHOLDER_CONTAINS="contains";
    public static final String CONTAINERHOLDER_CONTAINER_STATES="container_states";
    public static final String CONTAINERHOLDER_SERVICE_TYPE="service_type";
    public static final String CONTAINERHOLDER="ContainerHolder";
    public static final String APRON="Apron";
    public static final String ACTIVECONTAINERHOLDER_SCHEDULED_MOVEMENTS="scheduled_movements";
    public static final String ACTIVECONTAINERHOLDER_CAPABLE_OF="capable_of";
    public static final String ACTIVECONTAINERHOLDER="ActiveContainerHolder";
    public static final String YARD="Yard";
    public static final String CRANE="Crane";
    public static final String PASSIVECONTAINERHOLDER="PassiveContainerHolder";
    public static final String AGV="AGV";
    public static final String RMG="RMG";
    public static final String SHIP_SHIP_LENGTH="ship_length";
    public static final String SHIP="Ship";
    public static final String STATICCONTAINERHOLDER="StaticContainerHolder";
    public static final String TRAIN="Train";
    public static final String REQUESTRANDOMBAYMAP_Y_DIMENSION="y_dimension";
    public static final String REQUESTRANDOMBAYMAP_X_DIMENSION="x_dimension";
    public static final String REQUESTRANDOMBAYMAP_Z_DIMENSION="z_dimension";
    public static final String REQUESTRANDOMBAYMAP="RequestRandomBayMap";
    public static final String PROVIDEBLOCKADDRESS_SUITING_ADDRESS="suiting_address";
    public static final String PROVIDEBLOCKADDRESS="ProvideBlockAddress";
    public static final String ENROLLATHARBOR_SHIP_LENGTH="ship_length";
    public static final String ENROLLATHARBOR_REQUIRED_TURNOVER_CAPACITY="required_turnover_capacity";
    public static final String ENROLLATHARBOR="EnrollAtHarbor";
    public static final String STARTNEWCONTAINERHOLDER_RANDOMIZE="randomize";
    public static final String STARTNEWCONTAINERHOLDER_TO_BE_ADDED="to_be_added";
    public static final String STARTNEWCONTAINERHOLDER_POPULATE="populate";
    public static final String STARTNEWCONTAINERHOLDER_NAME="name";
    public static final String STARTNEWCONTAINERHOLDER="StartNewContainerHolder";
    public static final String PROVIDERANDOMLOADSEQUENCE_NEXT_STEP="next_step";
    public static final String PROVIDERANDOMLOADSEQUENCE="ProvideRandomLoadSequence";
    public static final String ACCEPTLOADOFFER_CORRESPONDS_TO="corresponds_to";
    public static final String ACCEPTLOADOFFER="AcceptLoadOffer";
    public static final String CALLFORPROPOSALSONLOADSTAGE_CORRESPONDS_TO="corresponds_to";
    public static final String CALLFORPROPOSALSONLOADSTAGE="CallForProposalsOnLoadStage";
    public static final String PROVIDEBAYMAP_PROVIDES="provides";
    public static final String PROVIDEBAYMAP="ProvideBayMap";
    public static final String REQUESTEXECUTELOADSEQUENCE_NEXT_STEP="next_step";
    public static final String REQUESTEXECUTELOADSEQUENCE="RequestExecuteLoadSequence";
    public static final String PROVIDEHARBOURSETUP_CURRENTLY_ACTIVE_CONTAINER_HOLDERS="currently_active_container_holders";
    public static final String PROVIDEHARBOURSETUP_CURRENT_HARBOUR_LAYOUT="current_harbour_layout";
    public static final String PROVIDEHARBOURSETUP="ProvideHarbourSetup";
    public static final String ANNOUNCELOADSTATUS_CORRESPONDS_TO="corresponds_to";
    public static final String ANNOUNCELOADSTATUS_LOAD_STATUS="load_status";
    public static final String ANNOUNCELOADSTATUS="AnnounceLoadStatus";
    public static final String PHY_MOVEMENT_PHY_STEPS="phy_Steps";
    public static final String PHY_MOVEMENT="Phy_Movement";
    public static final String REJECTLOADOFFER_CORRESPONDS_TO="corresponds_to";
    public static final String REJECTLOADOFFER="RejectLoadOffer";
    public static final String REQUESTPOPULATEDBAYMAP_POPULATE_ON="populate_on";
    public static final String REQUESTPOPULATEDBAYMAP="RequestPopulatedBayMap";
    public static final String REQUESTBLOCKADDRESS_PROVIDES_POPULATION="provides_population";
    public static final String REQUESTBLOCKADDRESS_SUBJECTED_TOC="subjected_toc";
    public static final String REQUESTBLOCKADDRESS_PROVIDES="provides";
    public static final String REQUESTBLOCKADDRESS="RequestBlockAddress";
    public static final String REQUESTONTOLOGYREPRESENTATION_AGENT_IN_QUESTION="agent_in_question";
    public static final String REQUESTONTOLOGYREPRESENTATION="RequestOntologyRepresentation";
    public static final String REQUESTRANDOMLOADSEQUENCE_PROVIDES_POPULATION="provides_population";
    public static final String REQUESTRANDOMLOADSEQUENCE_PROVIDES="provides";
    public static final String REQUESTRANDOMLOADSEQUENCE="RequestRandomLoadSequence";
    public static final String PROVIDEONTOLOGYREPRESENTATION_ACCORDING_ONTREP="according_ontrep";
    public static final String PROVIDEONTOLOGYREPRESENTATION="ProvideOntologyRepresentation";
    public static final String PROVIDEPOPULATEDBAYMAP_PROVIDES_POPULATION="provides_population";
    public static final String PROVIDEPOPULATEDBAYMAP_PROVIDES="provides";
    public static final String PROVIDEPOPULATEDBAYMAP="ProvidePopulatedBayMap";
    public static final String REQUESTENVIRONMENTRESET="RequestEnvironmentReset";
    public static final String ASSIGNHARBORQUAY_AVAILABLE_CRANES="available_cranes";
    public static final String ASSIGNHARBORQUAY_ASSIGNED_QUAY="assigned_quay";
    public static final String ASSIGNHARBORQUAY="AssignHarborQuay";
    public static final String PROPOSELOADOFFER_CORRESPONDS_TO="corresponds_to";
    public static final String PROPOSELOADOFFER_LOAD_OFFER="load_offer";
    public static final String PROPOSELOADOFFER="ProposeLoadOffer";
    public static final String REQUESTHARBOURSETUP="RequestHarbourSetup";
    public static final String PHY_ENVIRONMENTUPDATE_PHY_ENVIRONMENT="phy_environment";
    public static final String PHY_ENVIRONMENTUPDATE="Phy_EnvironmentUpdate";
    public static final String BERTH="Berth";
    public static final String STREET="Street";
    public static final String PHY_POSITION_PHY_Y="phy_y";
    public static final String PHY_POSITION_PHY_X="phy_x";
    public static final String PHY_POSITION="Phy_Position";
    public static final String INEXECUTION_LOAD_OFFER="load_offer";
    public static final String INEXECUTION="InExecution";
    public static final String HOLDING="Holding";
    public static final String ASSIGNED_LOAD_OFFER="load_offer";
    public static final String ASSIGNED="Assigned";
    public static final String TRANSPORTORDERCHAINSTATE_AT_ADDRESS="at_address";
    public static final String TRANSPORTORDERCHAINSTATE="TransportOrderChainState";
    public static final String CONTAINER_WEIGHT="weight";
    public static final String CONTAINER_BIC_CODE="bic_code";
    public static final String CONTAINER="Container";
    public static final String BAYMAP_Y_DIMENSION="y_dimension";
    public static final String BAYMAP_X_DIMENSION="x_dimension";
    public static final String BAYMAP_Z_DIMENSION="z_dimension";
    public static final String BAYMAP="BayMap";
    public static final String DESIGNATOR_ABSTRACT_DESIGNATION="abstract_designation";
    public static final String DESIGNATOR_AT_ADDRESS="at_address";
    public static final String DESIGNATOR_TYPE="type";
    public static final String DESIGNATOR_CONCRETE_DESIGNATION="concrete_designation";
    public static final String DESIGNATOR="Designator";
    public static final String PHY_ABSTRACTOBJECT_PHY_ID="phy_id";
    public static final String PHY_ABSTRACTOBJECT_PHY_SIZE="phy_size";
    public static final String PHY_ABSTRACTOBJECT_PHY_POSITION="phy_position";
    public static final String PHY_ABSTRACTOBJECT_PHY_PARENT="phy_parent";
    public static final String PHY_ABSTRACTOBJECT="Phy_AbstractObject";
    public static final String PHY_ENVIRONMENT_PHY_PROJECTNAME="phy_projectName";
    public static final String PHY_ENVIRONMENT_PHY_OBJECTS="phy_objects";
    public static final String PHY_ENVIRONMENT_PHY_SVGDOC="phy_svgDoc";
    public static final String PHY_ENVIRONMENT_PHY_ROOTPLAYGROUND="phy_rootPlayground";
    public static final String PHY_ENVIRONMENT_PHY_SCALE="phy_scale";
    public static final String PHY_ENVIRONMENT="Phy_Environment";
    public static final String MOVEMENT_MOVE_TO="move_to";
    public static final String MOVEMENT_BE_THERE_AT="be_there_at";
    public static final String MOVEMENT="Movement";
    public static final String LOADLIST_NEXT_STEP="next_step";
    public static final String LOADLIST_CONSISTS_OF="consists_of";
    public static final String LOADLIST="LoadList";
    public static final String PHY_PLAYGROUNDOBJECT_PHY_CHILDOBJECTS="phy_childObjects";
    public static final String PHY_PLAYGROUNDOBJECT="Phy_PlaygroundObject";
    public static final String TRANSPORTORDERCHAIN_IS_LINKED_BY="is_linked_by";
    public static final String TRANSPORTORDERCHAIN_TRANSPORTS="transports";
    public static final String TRANSPORTORDERCHAIN_TERMINATES_AT="terminates_at";
    public static final String TRANSPORTORDERCHAIN="TransportOrderChain";
    public static final String TRANSPORTORDER_STARTS_AT="starts_at";
    public static final String TRANSPORTORDER_TAKES_UNTIL="takes_until";
    public static final String TRANSPORTORDER_ENDS_AT="ends_at";
    public static final String TRANSPORTORDER="TransportOrder";
    public static final String TOCHASSTATE_STATE="state";
    public static final String TOCHASSTATE_SUBJECTED_TOC="subjected_toc";
    public static final String TOCHASSTATE="TOCHasState";
    public static final String BLOCKADDRESS_Y_DIMENSION="y_dimension";
    public static final String BLOCKADDRESS_X_DIMENSION="x_dimension";
    public static final String BLOCKADDRESS_LOCATES="locates";
    public static final String BLOCKADDRESS_Z_DIMENSION="z_dimension";
    public static final String BLOCKADDRESS="BlockAddress";
    public static final String PHY_SIZE_PHY_WIDTH="phy_width";
    public static final String PHY_SIZE_PHY_HEIGHT="phy_height";
    public static final String PHY_SIZE="Phy_Size";
    public static final String PLANNEDOUT="PlannedOut";
    public static final String QUAY="Quay";
    public static final String RESERVED="Reserved";
    public static final String PHY_SCALE_PHY_VALUE="phy_value";
    public static final String PHY_SCALE_PHY_PIXEL="phy_pixel";
    public static final String PHY_SCALE_PHY_UNIT="phy_unit";
    public static final String PHY_SCALE="Phy_Scale";
    public static final String PHY_AGENTOBJECT_PHY_MAXSPEED="phy_maxSpeed";
    public static final String PHY_AGENTOBJECT_PHY_COLLISIONPOINTS="phy_collisionPoints";
    public static final String PHY_AGENTOBJECT_PHY_AGENTCLASS="phy_agentClass";
    public static final String PHY_AGENTOBJECT_PHY_AID="phy_aid";
    public static final String PHY_AGENTOBJECT_PHY_CURRENTSPEED="phy_currentSpeed";
    public static final String PHY_AGENTOBJECT="Phy_AgentObject";
    public static final String YARDAREA="YardArea";
    public static final String DOMAIN_LIES_IN="lies_in";
    public static final String DOMAIN_HAS_SIZE="has_size";
    public static final String DOMAIN_IS_IN_POSITION="is_in_position";
    public static final String DOMAIN_HAS_SUBDOMAINS="has_subdomains";
    public static final String DOMAIN_ID="id";
    public static final String DOMAIN="Domain";
    public static final String ADMINISTERED="Administered";
    public static final String PHY_SPEED_PHY_SPEED="phy_speed";
    public static final String PHY_SPEED="Phy_Speed";
    public static final String PLANNEDIN="PlannedIn";
    public static final String APRONAREA="ApronArea";
    public static final String LAND="Land";
    public static final String RAIL="Rail";
    public static final String PHY_OBSTACLEOBJECT="Phy_ObstacleObject";
    public static final String FAILEDIN="FailedIn";
    public static final String PENDINGFORSUBCFP="PendingForSubCFP";
    public static final String TWENTYFOOTCONTAINER="TwentyFootContainer";
    public static final String ANNOUNCED="Announced";
    public static final String FAILEDOUT="FailedOut";
    public static final String PROPOSEDFOR="ProposedFor";
    public static final String SEA="Sea";
    public static final String HARBOUR="Harbour";

  /**
   * Constructor
  */
  private ContainerTerminalOntology(){ 
    super(ONTOLOGY_NAME, BasicOntology.getInstance());
    try { 

    // adding Concept(s)
    ConceptSchema harbourSchema = new ConceptSchema(HARBOUR);
    add(harbourSchema, contmas.ontology.Harbour.class);
    ConceptSchema seaSchema = new ConceptSchema(SEA);
    add(seaSchema, contmas.ontology.Sea.class);
    ConceptSchema proposedForSchema = new ConceptSchema(PROPOSEDFOR);
    add(proposedForSchema, contmas.ontology.ProposedFor.class);
    ConceptSchema failedOutSchema = new ConceptSchema(FAILEDOUT);
    add(failedOutSchema, contmas.ontology.FailedOut.class);
    ConceptSchema announcedSchema = new ConceptSchema(ANNOUNCED);
    add(announcedSchema, contmas.ontology.Announced.class);
    ConceptSchema twentyFootContainerSchema = new ConceptSchema(TWENTYFOOTCONTAINER);
    add(twentyFootContainerSchema, contmas.ontology.TwentyFootContainer.class);
    ConceptSchema pendingForSubCFPSchema = new ConceptSchema(PENDINGFORSUBCFP);
    add(pendingForSubCFPSchema, contmas.ontology.PendingForSubCFP.class);
    ConceptSchema failedInSchema = new ConceptSchema(FAILEDIN);
    add(failedInSchema, contmas.ontology.FailedIn.class);
    ConceptSchema phy_ObstacleObjectSchema = new ConceptSchema(PHY_OBSTACLEOBJECT);
    add(phy_ObstacleObjectSchema, contmas.ontology.Phy_ObstacleObject.class);
    ConceptSchema railSchema = new ConceptSchema(RAIL);
    add(railSchema, contmas.ontology.Rail.class);
    ConceptSchema landSchema = new ConceptSchema(LAND);
    add(landSchema, contmas.ontology.Land.class);
    ConceptSchema apronAreaSchema = new ConceptSchema(APRONAREA);
    add(apronAreaSchema, contmas.ontology.ApronArea.class);
    ConceptSchema plannedInSchema = new ConceptSchema(PLANNEDIN);
    add(plannedInSchema, contmas.ontology.PlannedIn.class);
    ConceptSchema phy_SpeedSchema = new ConceptSchema(PHY_SPEED);
    add(phy_SpeedSchema, contmas.ontology.Phy_Speed.class);
    ConceptSchema administeredSchema = new ConceptSchema(ADMINISTERED);
    add(administeredSchema, contmas.ontology.Administered.class);
    ConceptSchema domainSchema = new ConceptSchema(DOMAIN);
    add(domainSchema, contmas.ontology.Domain.class);
    ConceptSchema yardAreaSchema = new ConceptSchema(YARDAREA);
    add(yardAreaSchema, contmas.ontology.YardArea.class);
    ConceptSchema phy_AgentObjectSchema = new ConceptSchema(PHY_AGENTOBJECT);
    add(phy_AgentObjectSchema, contmas.ontology.Phy_AgentObject.class);
    ConceptSchema phy_ScaleSchema = new ConceptSchema(PHY_SCALE);
    add(phy_ScaleSchema, contmas.ontology.Phy_Scale.class);
    ConceptSchema reservedSchema = new ConceptSchema(RESERVED);
    add(reservedSchema, contmas.ontology.Reserved.class);
    ConceptSchema quaySchema = new ConceptSchema(QUAY);
    add(quaySchema, contmas.ontology.Quay.class);
    ConceptSchema plannedOutSchema = new ConceptSchema(PLANNEDOUT);
    add(plannedOutSchema, contmas.ontology.PlannedOut.class);
    ConceptSchema phy_SizeSchema = new ConceptSchema(PHY_SIZE);
    add(phy_SizeSchema, contmas.ontology.Phy_Size.class);
    ConceptSchema blockAddressSchema = new ConceptSchema(BLOCKADDRESS);
    add(blockAddressSchema, contmas.ontology.BlockAddress.class);
    ConceptSchema tocHasStateSchema = new ConceptSchema(TOCHASSTATE);
    add(tocHasStateSchema, contmas.ontology.TOCHasState.class);
    ConceptSchema transportOrderSchema = new ConceptSchema(TRANSPORTORDER);
    add(transportOrderSchema, contmas.ontology.TransportOrder.class);
    ConceptSchema transportOrderChainSchema = new ConceptSchema(TRANSPORTORDERCHAIN);
    add(transportOrderChainSchema, contmas.ontology.TransportOrderChain.class);
    ConceptSchema phy_PlaygroundObjectSchema = new ConceptSchema(PHY_PLAYGROUNDOBJECT);
    add(phy_PlaygroundObjectSchema, contmas.ontology.Phy_PlaygroundObject.class);
    ConceptSchema loadListSchema = new ConceptSchema(LOADLIST);
    add(loadListSchema, contmas.ontology.LoadList.class);
    ConceptSchema movementSchema = new ConceptSchema(MOVEMENT);
    add(movementSchema, contmas.ontology.Movement.class);
    ConceptSchema phy_EnvironmentSchema = new ConceptSchema(PHY_ENVIRONMENT);
    add(phy_EnvironmentSchema, contmas.ontology.Phy_Environment.class);
    ConceptSchema phy_AbstractObjectSchema = new ConceptSchema(PHY_ABSTRACTOBJECT);
    add(phy_AbstractObjectSchema, contmas.ontology.Phy_AbstractObject.class);
    ConceptSchema designatorSchema = new ConceptSchema(DESIGNATOR);
    add(designatorSchema, contmas.ontology.Designator.class);
    ConceptSchema bayMapSchema = new ConceptSchema(BAYMAP);
    add(bayMapSchema, contmas.ontology.BayMap.class);
    ConceptSchema containerSchema = new ConceptSchema(CONTAINER);
    add(containerSchema, contmas.ontology.Container.class);
    ConceptSchema transportOrderChainStateSchema = new ConceptSchema(TRANSPORTORDERCHAINSTATE);
    add(transportOrderChainStateSchema, contmas.ontology.TransportOrderChainState.class);
    ConceptSchema assignedSchema = new ConceptSchema(ASSIGNED);
    add(assignedSchema, contmas.ontology.Assigned.class);
    ConceptSchema holdingSchema = new ConceptSchema(HOLDING);
    add(holdingSchema, contmas.ontology.Holding.class);
    ConceptSchema inExecutionSchema = new ConceptSchema(INEXECUTION);
    add(inExecutionSchema, contmas.ontology.InExecution.class);
    ConceptSchema phy_PositionSchema = new ConceptSchema(PHY_POSITION);
    add(phy_PositionSchema, contmas.ontology.Phy_Position.class);
    ConceptSchema streetSchema = new ConceptSchema(STREET);
    add(streetSchema, contmas.ontology.Street.class);
    ConceptSchema berthSchema = new ConceptSchema(BERTH);
    add(berthSchema, contmas.ontology.Berth.class);

    // adding AgentAction(s)
    AgentActionSchema phy_EnvironmentUpdateSchema = new AgentActionSchema(PHY_ENVIRONMENTUPDATE);
    add(phy_EnvironmentUpdateSchema, contmas.ontology.Phy_EnvironmentUpdate.class);
    AgentActionSchema requestHarbourSetupSchema = new AgentActionSchema(REQUESTHARBOURSETUP);
    add(requestHarbourSetupSchema, contmas.ontology.RequestHarbourSetup.class);
    AgentActionSchema proposeLoadOfferSchema = new AgentActionSchema(PROPOSELOADOFFER);
    add(proposeLoadOfferSchema, contmas.ontology.ProposeLoadOffer.class);
    AgentActionSchema assignHarborQuaySchema = new AgentActionSchema(ASSIGNHARBORQUAY);
    add(assignHarborQuaySchema, contmas.ontology.AssignHarborQuay.class);
    AgentActionSchema requestEnvironmentResetSchema = new AgentActionSchema(REQUESTENVIRONMENTRESET);
    add(requestEnvironmentResetSchema, contmas.ontology.RequestEnvironmentReset.class);
    AgentActionSchema providePopulatedBayMapSchema = new AgentActionSchema(PROVIDEPOPULATEDBAYMAP);
    add(providePopulatedBayMapSchema, contmas.ontology.ProvidePopulatedBayMap.class);
    AgentActionSchema provideOntologyRepresentationSchema = new AgentActionSchema(PROVIDEONTOLOGYREPRESENTATION);
    add(provideOntologyRepresentationSchema, contmas.ontology.ProvideOntologyRepresentation.class);
    AgentActionSchema requestRandomLoadSequenceSchema = new AgentActionSchema(REQUESTRANDOMLOADSEQUENCE);
    add(requestRandomLoadSequenceSchema, contmas.ontology.RequestRandomLoadSequence.class);
    AgentActionSchema requestOntologyRepresentationSchema = new AgentActionSchema(REQUESTONTOLOGYREPRESENTATION);
    add(requestOntologyRepresentationSchema, contmas.ontology.RequestOntologyRepresentation.class);
    AgentActionSchema requestBlockAddressSchema = new AgentActionSchema(REQUESTBLOCKADDRESS);
    add(requestBlockAddressSchema, contmas.ontology.RequestBlockAddress.class);
    AgentActionSchema requestPopulatedBayMapSchema = new AgentActionSchema(REQUESTPOPULATEDBAYMAP);
    add(requestPopulatedBayMapSchema, contmas.ontology.RequestPopulatedBayMap.class);
    AgentActionSchema rejectLoadOfferSchema = new AgentActionSchema(REJECTLOADOFFER);
    add(rejectLoadOfferSchema, contmas.ontology.RejectLoadOffer.class);
    AgentActionSchema phy_MovementSchema = new AgentActionSchema(PHY_MOVEMENT);
    add(phy_MovementSchema, contmas.ontology.Phy_Movement.class);
    AgentActionSchema announceLoadStatusSchema = new AgentActionSchema(ANNOUNCELOADSTATUS);
    add(announceLoadStatusSchema, contmas.ontology.AnnounceLoadStatus.class);
    AgentActionSchema provideHarbourSetupSchema = new AgentActionSchema(PROVIDEHARBOURSETUP);
    add(provideHarbourSetupSchema, contmas.ontology.ProvideHarbourSetup.class);
    AgentActionSchema requestExecuteLoadSequenceSchema = new AgentActionSchema(REQUESTEXECUTELOADSEQUENCE);
    add(requestExecuteLoadSequenceSchema, contmas.ontology.RequestExecuteLoadSequence.class);
    AgentActionSchema provideBayMapSchema = new AgentActionSchema(PROVIDEBAYMAP);
    add(provideBayMapSchema, contmas.ontology.ProvideBayMap.class);
    AgentActionSchema callForProposalsOnLoadStageSchema = new AgentActionSchema(CALLFORPROPOSALSONLOADSTAGE);
    add(callForProposalsOnLoadStageSchema, contmas.ontology.CallForProposalsOnLoadStage.class);
    AgentActionSchema acceptLoadOfferSchema = new AgentActionSchema(ACCEPTLOADOFFER);
    add(acceptLoadOfferSchema, contmas.ontology.AcceptLoadOffer.class);
    AgentActionSchema provideRandomLoadSequenceSchema = new AgentActionSchema(PROVIDERANDOMLOADSEQUENCE);
    add(provideRandomLoadSequenceSchema, contmas.ontology.ProvideRandomLoadSequence.class);
    AgentActionSchema startNewContainerHolderSchema = new AgentActionSchema(STARTNEWCONTAINERHOLDER);
    add(startNewContainerHolderSchema, contmas.ontology.StartNewContainerHolder.class);
    AgentActionSchema enrollAtHarborSchema = new AgentActionSchema(ENROLLATHARBOR);
    add(enrollAtHarborSchema, contmas.ontology.EnrollAtHarbor.class);
    AgentActionSchema provideBlockAddressSchema = new AgentActionSchema(PROVIDEBLOCKADDRESS);
    add(provideBlockAddressSchema, contmas.ontology.ProvideBlockAddress.class);
    AgentActionSchema requestRandomBayMapSchema = new AgentActionSchema(REQUESTRANDOMBAYMAP);
    add(requestRandomBayMapSchema, contmas.ontology.RequestRandomBayMap.class);

    // adding AID(s)
    ConceptSchema trainSchema = new ConceptSchema(TRAIN);
    add(trainSchema, contmas.ontology.Train.class);
    ConceptSchema staticContainerHolderSchema = new ConceptSchema(STATICCONTAINERHOLDER);
    add(staticContainerHolderSchema, contmas.ontology.StaticContainerHolder.class);
    ConceptSchema shipSchema = new ConceptSchema(SHIP);
    add(shipSchema, contmas.ontology.Ship.class);
    ConceptSchema rmgSchema = new ConceptSchema(RMG);
    add(rmgSchema, contmas.ontology.RMG.class);
    ConceptSchema agvSchema = new ConceptSchema(AGV);
    add(agvSchema, contmas.ontology.AGV.class);
    ConceptSchema passiveContainerHolderSchema = new ConceptSchema(PASSIVECONTAINERHOLDER);
    add(passiveContainerHolderSchema, contmas.ontology.PassiveContainerHolder.class);
    ConceptSchema craneSchema = new ConceptSchema(CRANE);
    add(craneSchema, contmas.ontology.Crane.class);
    ConceptSchema yardSchema = new ConceptSchema(YARD);
    add(yardSchema, contmas.ontology.Yard.class);
    ConceptSchema activeContainerHolderSchema = new ConceptSchema(ACTIVECONTAINERHOLDER);
    add(activeContainerHolderSchema, contmas.ontology.ActiveContainerHolder.class);
    ConceptSchema apronSchema = new ConceptSchema(APRON);
    add(apronSchema, contmas.ontology.Apron.class);
    ConceptSchema containerHolderSchema = new ConceptSchema(CONTAINERHOLDER);
    add(containerHolderSchema, contmas.ontology.ContainerHolder.class);
    ConceptSchema straddleCarrierSchema = new ConceptSchema(STRADDLECARRIER);
    add(straddleCarrierSchema, contmas.ontology.StraddleCarrier.class);

    // adding Predicate(s)


    // adding fields
    phy_SpeedSchema.add(PHY_SPEED_PHY_SPEED, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    domainSchema.add(DOMAIN_ID, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    domainSchema.add(DOMAIN_HAS_SUBDOMAINS, domainSchema, 0, ObjectSchema.UNLIMITED);
    domainSchema.add(DOMAIN_IS_IN_POSITION, phy_PositionSchema, ObjectSchema.OPTIONAL);
    domainSchema.add(DOMAIN_HAS_SIZE, phy_SizeSchema, ObjectSchema.OPTIONAL);
    domainSchema.add(DOMAIN_LIES_IN, domainSchema, ObjectSchema.OPTIONAL);
    phy_AgentObjectSchema.add(PHY_AGENTOBJECT_PHY_CURRENTSPEED, phy_SpeedSchema, ObjectSchema.OPTIONAL);
    phy_AgentObjectSchema.add(PHY_AGENTOBJECT_PHY_AID, (ConceptSchema)getSchema(BasicOntology.AID), ObjectSchema.OPTIONAL);
    phy_AgentObjectSchema.add(PHY_AGENTOBJECT_PHY_AGENTCLASS, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    phy_AgentObjectSchema.add(PHY_AGENTOBJECT_PHY_COLLISIONPOINTS, new ConceptSchema("Concept"), 0, ObjectSchema.UNLIMITED);
    phy_AgentObjectSchema.add(PHY_AGENTOBJECT_PHY_MAXSPEED, phy_SpeedSchema, ObjectSchema.OPTIONAL);
    phy_ScaleSchema.add(PHY_SCALE_PHY_UNIT, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    phy_ScaleSchema.add(PHY_SCALE_PHY_PIXEL, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    phy_ScaleSchema.add(PHY_SCALE_PHY_VALUE, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    phy_SizeSchema.add(PHY_SIZE_PHY_HEIGHT, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    phy_SizeSchema.add(PHY_SIZE_PHY_WIDTH, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    blockAddressSchema.add(BLOCKADDRESS_Z_DIMENSION, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    blockAddressSchema.add(BLOCKADDRESS_LOCATES, containerSchema, ObjectSchema.OPTIONAL);
    blockAddressSchema.add(BLOCKADDRESS_X_DIMENSION, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    blockAddressSchema.add(BLOCKADDRESS_Y_DIMENSION, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    tocHasStateSchema.add(TOCHASSTATE_SUBJECTED_TOC, transportOrderChainSchema, ObjectSchema.OPTIONAL);
    tocHasStateSchema.add(TOCHASSTATE_STATE, transportOrderChainStateSchema, ObjectSchema.OPTIONAL);
    transportOrderSchema.add(TRANSPORTORDER_ENDS_AT, designatorSchema, ObjectSchema.OPTIONAL);
    transportOrderSchema.add(TRANSPORTORDER_TAKES_UNTIL, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    transportOrderSchema.add(TRANSPORTORDER_STARTS_AT, designatorSchema, ObjectSchema.OPTIONAL);
    transportOrderChainSchema.add(TRANSPORTORDERCHAIN_TERMINATES_AT, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    transportOrderChainSchema.add(TRANSPORTORDERCHAIN_TRANSPORTS, containerSchema, ObjectSchema.OPTIONAL);
    transportOrderChainSchema.add(TRANSPORTORDERCHAIN_IS_LINKED_BY, transportOrderSchema, 0, ObjectSchema.UNLIMITED);
    phy_PlaygroundObjectSchema.add(PHY_PLAYGROUNDOBJECT_PHY_CHILDOBJECTS, phy_AbstractObjectSchema, 0, ObjectSchema.UNLIMITED);
    loadListSchema.add(LOADLIST_CONSISTS_OF, transportOrderChainSchema, 0, ObjectSchema.UNLIMITED);
    loadListSchema.add(LOADLIST_NEXT_STEP, loadListSchema, ObjectSchema.OPTIONAL);
    movementSchema.add(MOVEMENT_BE_THERE_AT, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    movementSchema.add(MOVEMENT_MOVE_TO, phy_PositionSchema, ObjectSchema.OPTIONAL);
    phy_EnvironmentSchema.add(PHY_ENVIRONMENT_PHY_SCALE, phy_ScaleSchema, ObjectSchema.OPTIONAL);
    phy_EnvironmentSchema.add(PHY_ENVIRONMENT_PHY_ROOTPLAYGROUND, phy_PlaygroundObjectSchema, ObjectSchema.OPTIONAL);
    phy_EnvironmentSchema.add(PHY_ENVIRONMENT_PHY_SVGDOC, new ConceptSchema("Concept"), ObjectSchema.OPTIONAL);
    phy_EnvironmentSchema.add(PHY_ENVIRONMENT_PHY_OBJECTS, phy_AbstractObjectSchema, 0, ObjectSchema.UNLIMITED);
    phy_EnvironmentSchema.add(PHY_ENVIRONMENT_PHY_PROJECTNAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    phy_AbstractObjectSchema.add(PHY_ABSTRACTOBJECT_PHY_PARENT, phy_PlaygroundObjectSchema, ObjectSchema.OPTIONAL);
    phy_AbstractObjectSchema.add(PHY_ABSTRACTOBJECT_PHY_POSITION, phy_PositionSchema, ObjectSchema.OPTIONAL);
    phy_AbstractObjectSchema.add(PHY_ABSTRACTOBJECT_PHY_SIZE, phy_SizeSchema, ObjectSchema.OPTIONAL);
    phy_AbstractObjectSchema.add(PHY_ABSTRACTOBJECT_PHY_ID, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    designatorSchema.add(DESIGNATOR_CONCRETE_DESIGNATION, (ConceptSchema)getSchema(BasicOntology.AID), ObjectSchema.OPTIONAL);
    designatorSchema.add(DESIGNATOR_TYPE, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    designatorSchema.add(DESIGNATOR_AT_ADDRESS, blockAddressSchema, ObjectSchema.OPTIONAL);
    designatorSchema.add(DESIGNATOR_ABSTRACT_DESIGNATION, domainSchema, ObjectSchema.OPTIONAL);
    bayMapSchema.add(BAYMAP_Z_DIMENSION, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    bayMapSchema.add(BAYMAP_X_DIMENSION, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    bayMapSchema.add(BAYMAP_Y_DIMENSION, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    containerSchema.add(CONTAINER_BIC_CODE, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    containerSchema.add(CONTAINER_WEIGHT, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    transportOrderChainStateSchema.add(TRANSPORTORDERCHAINSTATE_AT_ADDRESS, blockAddressSchema, ObjectSchema.OPTIONAL);
    assignedSchema.add(ASSIGNED_LOAD_OFFER, transportOrderSchema, ObjectSchema.OPTIONAL);
    inExecutionSchema.add(INEXECUTION_LOAD_OFFER, transportOrderSchema, ObjectSchema.OPTIONAL);
    phy_PositionSchema.add(PHY_POSITION_PHY_X, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    phy_PositionSchema.add(PHY_POSITION_PHY_Y, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    phy_EnvironmentUpdateSchema.add(PHY_ENVIRONMENTUPDATE_PHY_ENVIRONMENT, phy_EnvironmentSchema, ObjectSchema.OPTIONAL);
    proposeLoadOfferSchema.add(PROPOSELOADOFFER_LOAD_OFFER, transportOrderSchema, ObjectSchema.OPTIONAL);
    proposeLoadOfferSchema.add(PROPOSELOADOFFER_CORRESPONDS_TO, transportOrderChainSchema, ObjectSchema.OPTIONAL);
    assignHarborQuaySchema.add(ASSIGNHARBORQUAY_ASSIGNED_QUAY, quaySchema, ObjectSchema.OPTIONAL);
    assignHarborQuaySchema.add(ASSIGNHARBORQUAY_AVAILABLE_CRANES, (ConceptSchema)getSchema(BasicOntology.AID), 0, ObjectSchema.UNLIMITED);
    providePopulatedBayMapSchema.add(PROVIDEPOPULATEDBAYMAP_PROVIDES, bayMapSchema, ObjectSchema.OPTIONAL);
    providePopulatedBayMapSchema.add(PROVIDEPOPULATEDBAYMAP_PROVIDES_POPULATION, tocHasStateSchema, 0, ObjectSchema.UNLIMITED);
    provideOntologyRepresentationSchema.add(PROVIDEONTOLOGYREPRESENTATION_ACCORDING_ONTREP, containerHolderSchema, ObjectSchema.OPTIONAL);
    requestRandomLoadSequenceSchema.add(REQUESTRANDOMLOADSEQUENCE_PROVIDES, bayMapSchema, ObjectSchema.OPTIONAL);
    requestRandomLoadSequenceSchema.add(REQUESTRANDOMLOADSEQUENCE_PROVIDES_POPULATION, tocHasStateSchema, 0, ObjectSchema.UNLIMITED);
    requestOntologyRepresentationSchema.add(REQUESTONTOLOGYREPRESENTATION_AGENT_IN_QUESTION, (ConceptSchema)getSchema(BasicOntology.AID), ObjectSchema.OPTIONAL);
    requestBlockAddressSchema.add(REQUESTBLOCKADDRESS_PROVIDES, bayMapSchema, ObjectSchema.OPTIONAL);
    requestBlockAddressSchema.add(REQUESTBLOCKADDRESS_SUBJECTED_TOC, transportOrderChainSchema, ObjectSchema.OPTIONAL);
    requestBlockAddressSchema.add(REQUESTBLOCKADDRESS_PROVIDES_POPULATION, tocHasStateSchema, 0, ObjectSchema.UNLIMITED);
    requestPopulatedBayMapSchema.add(REQUESTPOPULATEDBAYMAP_POPULATE_ON, bayMapSchema, ObjectSchema.OPTIONAL);
    rejectLoadOfferSchema.add(REJECTLOADOFFER_CORRESPONDS_TO, transportOrderChainSchema, ObjectSchema.OPTIONAL);
    phy_MovementSchema.add(PHY_MOVEMENT_PHY_STEPS, phy_PositionSchema, 0, ObjectSchema.UNLIMITED);
    announceLoadStatusSchema.add(ANNOUNCELOADSTATUS_LOAD_STATUS, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    announceLoadStatusSchema.add(ANNOUNCELOADSTATUS_CORRESPONDS_TO, transportOrderChainSchema, ObjectSchema.OPTIONAL);
    provideHarbourSetupSchema.add(PROVIDEHARBOURSETUP_CURRENT_HARBOUR_LAYOUT, domainSchema, ObjectSchema.OPTIONAL);
    provideHarbourSetupSchema.add(PROVIDEHARBOURSETUP_CURRENTLY_ACTIVE_CONTAINER_HOLDERS, containerHolderSchema, 0, ObjectSchema.UNLIMITED);
    requestExecuteLoadSequenceSchema.add(REQUESTEXECUTELOADSEQUENCE_NEXT_STEP, loadListSchema, ObjectSchema.OPTIONAL);
    provideBayMapSchema.add(PROVIDEBAYMAP_PROVIDES, bayMapSchema, ObjectSchema.OPTIONAL);
    callForProposalsOnLoadStageSchema.add(CALLFORPROPOSALSONLOADSTAGE_CORRESPONDS_TO, transportOrderChainSchema, ObjectSchema.OPTIONAL);
    acceptLoadOfferSchema.add(ACCEPTLOADOFFER_CORRESPONDS_TO, transportOrderChainSchema, ObjectSchema.OPTIONAL);
    provideRandomLoadSequenceSchema.add(PROVIDERANDOMLOADSEQUENCE_NEXT_STEP, loadListSchema, ObjectSchema.OPTIONAL);
    startNewContainerHolderSchema.add(STARTNEWCONTAINERHOLDER_NAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    startNewContainerHolderSchema.add(STARTNEWCONTAINERHOLDER_POPULATE, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);
    startNewContainerHolderSchema.add(STARTNEWCONTAINERHOLDER_TO_BE_ADDED, containerHolderSchema, ObjectSchema.OPTIONAL);
    startNewContainerHolderSchema.add(STARTNEWCONTAINERHOLDER_RANDOMIZE, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);
    enrollAtHarborSchema.add(ENROLLATHARBOR_REQUIRED_TURNOVER_CAPACITY, loadListSchema, ObjectSchema.OPTIONAL);
    enrollAtHarborSchema.add(ENROLLATHARBOR_SHIP_LENGTH, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    provideBlockAddressSchema.add(PROVIDEBLOCKADDRESS_SUITING_ADDRESS, blockAddressSchema, ObjectSchema.OPTIONAL);
    requestRandomBayMapSchema.add(REQUESTRANDOMBAYMAP_Z_DIMENSION, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    requestRandomBayMapSchema.add(REQUESTRANDOMBAYMAP_X_DIMENSION, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    requestRandomBayMapSchema.add(REQUESTRANDOMBAYMAP_Y_DIMENSION, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    shipSchema.add(SHIP_SHIP_LENGTH, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    activeContainerHolderSchema.add(ACTIVECONTAINERHOLDER_CAPABLE_OF, domainSchema, 0, ObjectSchema.UNLIMITED);
    activeContainerHolderSchema.add(ACTIVECONTAINERHOLDER_SCHEDULED_MOVEMENTS, movementSchema, 0, ObjectSchema.UNLIMITED);
    containerHolderSchema.add(CONTAINERHOLDER_SERVICE_TYPE, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    containerHolderSchema.add(CONTAINERHOLDER_CONTAINER_STATES, tocHasStateSchema, 0, ObjectSchema.UNLIMITED);
    containerHolderSchema.add(CONTAINERHOLDER_CONTAINS, bayMapSchema, ObjectSchema.OPTIONAL);
    containerHolderSchema.add(CONTAINERHOLDER_LIVES_IN, domainSchema, ObjectSchema.OPTIONAL);
    containerHolderSchema.add(CONTAINERHOLDER_LOCALNAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    containerHolderSchema.add(CONTAINERHOLDER_IS_IN_POSITION2, phy_PositionSchema, ObjectSchema.OPTIONAL);
    containerHolderSchema.add(CONTAINERHOLDER_CONTRACTORS, (ConceptSchema)getSchema(BasicOntology.AID), 0, ObjectSchema.UNLIMITED);

    // adding name mappings

    // adding inheritance
    harbourSchema.addSuperSchema(domainSchema);
    seaSchema.addSuperSchema(domainSchema);
    proposedForSchema.addSuperSchema(reservedSchema);
    failedOutSchema.addSuperSchema(holdingSchema);
    announcedSchema.addSuperSchema(holdingSchema);
    twentyFootContainerSchema.addSuperSchema(containerSchema);
    pendingForSubCFPSchema.addSuperSchema(reservedSchema);
    failedInSchema.addSuperSchema(transportOrderChainStateSchema);
    phy_ObstacleObjectSchema.addSuperSchema(phy_AbstractObjectSchema);
    railSchema.addSuperSchema(domainSchema);
    landSchema.addSuperSchema(domainSchema);
    apronAreaSchema.addSuperSchema(domainSchema);
    plannedInSchema.addSuperSchema(reservedSchema);
    administeredSchema.addSuperSchema(holdingSchema);
    yardAreaSchema.addSuperSchema(domainSchema);
    phy_AgentObjectSchema.addSuperSchema(phy_AbstractObjectSchema);
    reservedSchema.addSuperSchema(transportOrderChainStateSchema);
    quaySchema.addSuperSchema(domainSchema);
    plannedOutSchema.addSuperSchema(holdingSchema);
    phy_PlaygroundObjectSchema.addSuperSchema(phy_AbstractObjectSchema);
    assignedSchema.addSuperSchema(holdingSchema);
    holdingSchema.addSuperSchema(transportOrderChainStateSchema);
    inExecutionSchema.addSuperSchema(reservedSchema);
    streetSchema.addSuperSchema(domainSchema);
    berthSchema.addSuperSchema(domainSchema);
    trainSchema.addSuperSchema(staticContainerHolderSchema);
    staticContainerHolderSchema.addSuperSchema(containerHolderSchema);
    shipSchema.addSuperSchema(staticContainerHolderSchema);
    rmgSchema.addSuperSchema(activeContainerHolderSchema);
    agvSchema.addSuperSchema(passiveContainerHolderSchema);
    passiveContainerHolderSchema.addSuperSchema(containerHolderSchema);
    craneSchema.addSuperSchema(activeContainerHolderSchema);
    yardSchema.addSuperSchema(staticContainerHolderSchema);
    activeContainerHolderSchema.addSuperSchema(containerHolderSchema);
    apronSchema.addSuperSchema(staticContainerHolderSchema);
    straddleCarrierSchema.addSuperSchema(activeContainerHolderSchema);

   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
  }
