package org.uhafactory.test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@ActiveProfiles("test")
public class RepositoryTestBase {
}
