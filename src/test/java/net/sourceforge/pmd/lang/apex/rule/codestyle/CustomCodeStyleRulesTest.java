/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.apex.rule.codestyle;

import net.sourceforge.pmd.testframework.SimpleAggregatorTst;

public class CustomCodeStyleRulesTest extends SimpleAggregatorTst {

    private static final String RULESET = "category/apex/Customcodestyle.xml";
  /*  private static final String RULESET_OBJECT = "category/xml/object/Customcodestyle.xml";
    private static final String RULESET_COMP = "category/xml/component/Customcodestyle.xml";*/

    @Override
    public void setUp() {
    	/*addRule(RULESET, "CustomTestClassNamingConvention");
       addRule(RULESET, "CustomVariableNamingConvention");
       addRule(RULESET,"CustomControllerNamingConvention");
        addRule(RULESET,"CustomTriggerNamingConvention");
        addRule(RULESET,"CustomUnitTestShouldHaveStartStopTest");
       addRule(RULESET,"CustomNoTestWithinNormalClass");
        addRule(RULESET,"CustomTestMethodSignature"); 
         addRule(RULESET,"CustomAvoidSymbolsInClassNameRule");
          addRule(RULESET,"CustomAvoidHardCodedURLRule");
          addRule(RULESET_OBJECT,"CustomAvoidHardCodedURLInObjectRule");
          addRule(RULESET_COMP,"CustomAvoidHardCodedURLInComponentRule");*/
          //addRule(RULESET,"CustomNoAsyncMethodInsideLoop");
      // addRule(RULESET,"CustomNoHardcodedId");
        //addRule(RULESET,"CustomNoGenericDescForLightningComponent");
      // addRule(RULESET,"CustomOneTriggerPerObject");
       //addRule(RULESET,"CustomAvoidLogicInTrigger");
       addRule(RULESET,"UnUsedLocalVariableRule");
        //addRule(RULESET,"CustomAsynchronusApexShouldBeBulkified");
    }
}
