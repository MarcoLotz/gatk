package org.broadinstitute.hellbender.tools.walkers.varianteval;

import org.broadinstitute.hellbender.CommandLineProgramTest;
import org.broadinstitute.hellbender.testutils.IntegrationTestSpec;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class AlleleFrequencyQCIntegrationTest extends CommandLineProgramTest {

    private String evalVcf = getToolTestDataDir() + File.separator + "af.na12878_array.vcf";
    private String comparisonVcf = getToolTestDataDir() + File.separator + "af.thousand_genomes.10sites.vcf";


    private String getExpectedFile(String testName) {
        return getToolTestDataDir() + File.separator + "expected" + File.separator + testName + ".expected.txt";
    }


    @Test(groups = {"R"})
    public void testAlleleFrequencyIntegrationTest() throws IOException {
        String name = "testAFQCIntegration";

        IntegrationTestSpec spec = new IntegrationTestSpec(
                " -R " + b37Reference +
                        " --eval " + evalVcf +
                        " --comp " + comparisonVcf +
                        " -eval:thousand_genomes " + comparisonVcf +
                        " -L " + comparisonVcf +
                        " -O %s"
                , Arrays.asList(getExpectedFile(name)));

        spec.executeTest(name, this);
    }

}

