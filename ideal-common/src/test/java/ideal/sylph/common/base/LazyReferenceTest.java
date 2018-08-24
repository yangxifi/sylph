/*
 * Copyright (C) 2018 The Sylph Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ideal.sylph.common.base;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Supplier;

public class LazyReferenceTest
{

    @Test
    public void goLazy()
            throws IOException
    {
        final LazyReference<Connection> connection = LazyReference.goLazy(() -> {
            try {
                return DriverManager.getConnection("jdbc:url");
            }
            catch (SQLException e) {
                throw new RuntimeException("Connection create fail", e);
            }
        });

        Assert.assertNotNull(Serializables.serialize(connection));
    }
}