package net.kr9ly.thinfw.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.kr9ly.thinfw.error.SampleAppException;
import net.kr9ly.thinfw.inject.AppInject;
import net.kr9ly.thinfw.model.TestModel;
import net.kr9ly.thinfw.model.TestModel2;
import net.kr9ly.thinfw.response.SampleIndexResponse;
import org.glassfish.jersey.server.mvc.Template;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Copyright 2016 kr9ly
 * <br />
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <br />
 * http://www.apache.org/licenses/LICENSE-2.0
 * <br />
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
@Api(value = "/", description = "APIサンプル")
public class IndexResource {

    @AppInject TestModel testModel;

    @AppInject TestModel2 testModel2;

    @GET
    @Template(name = "/index")
    @ApiOperation("APIドキュメントのサンプルです。")
    public SampleIndexResponse index(@QueryParam("id") String id) throws SampleAppException {
        SampleIndexResponse resp = new SampleIndexResponse();
        resp.foo = testModel.test() + ":" + testModel2.test() + ":" + id;
        return resp;
    }
}
