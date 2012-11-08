package it.grid.storm.xmlrpc;
/*
*
*  Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2012
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/

import it.grid.storm.srm.types.ArrayOfTExtraInfo;
import it.grid.storm.srm.types.ArrayOfTSpaceToken;
import it.grid.storm.srm.types.RecursionLevel;
import it.grid.storm.srm.types.TLifeTimeInSeconds;
import it.grid.storm.srm.types.TRequestToken;
import it.grid.storm.srm.types.TRetentionPolicyInfo;
import it.grid.storm.srm.types.TSizeInBytes;
import it.grid.storm.srm.types.TSpaceToken;
import it.grid.storm.xmlrpc.executors.AbortFilesExecutor;
import it.grid.storm.xmlrpc.executors.AbortRequestExecutor;
import it.grid.storm.xmlrpc.executors.LsExecutor;
import it.grid.storm.xmlrpc.executors.MkdirExecutor;
import it.grid.storm.xmlrpc.executors.MvExecutor;
import it.grid.storm.xmlrpc.executors.PingExecutor;
import it.grid.storm.xmlrpc.executors.PtGExecutor;
import it.grid.storm.xmlrpc.executors.PtPExecutor;
import it.grid.storm.xmlrpc.executors.PutDoneExecutor;
import it.grid.storm.xmlrpc.executors.ReleaseFilesExecutor;
import it.grid.storm.xmlrpc.executors.RmExecutor;
import it.grid.storm.xmlrpc.executors.RmdirExecutor;
import it.grid.storm.xmlrpc.outputdata.*;
import it.grid.storm.xmlrpc.remote.synchcall;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import redstone.xmlrpc.XmlRpcProxy;

/**
 * @author Michele Dibenedetto
 *
 */
public class BackendApi
{

    private synchcall storm;

    /**
     * @param backendServer
     * @param backendPort
     * @throws Exception
     */
    public BackendApi(String backendServer, Long backendPort) throws ApiException
    {
        URL url;
        try
        {
            url = new URL("http://" + backendServer + ":" + backendPort + "/RPC2");
        } catch(MalformedURLException e)
        {
            throw new ApiException(e);
        }
        storm = (synchcall) XmlRpcProxy.createProxy(url, new Class[] { synchcall.class }, false);
    }

    /**
     * @param userDN the dn of the user 
     * @param userFQANS a list of not-null/not-empty fqan values
     * @return an object representing the operation result
     * @throws ApiException if is unable to perform the remote call
     * @throws IllegalArgumentException if the provided elements are invalid
     */
    public PingOutputData ping(String userDN, List<String> userFQANS) throws ApiException, IllegalArgumentException
    {
        return PingExecutor.execute(storm, userDN, userFQANS);
    }
    
    public SurlArrayRequestOutputData putDone(String userDN, List<String> userFQANS, List<String> surls,
            TRequestToken requestToken) throws ApiException
    {
        return PutDoneExecutor.execute(storm, userDN, userFQANS, surls, requestToken);
    }

    public LsOutputData ls(String userDN, List<String> userFQANS, List<String> surls) throws ApiException
    {
        return LsExecutor.execute(storm, userDN, userFQANS, surls);
    }

    /**
     * @param userDN
     * @param userFQANS
     * @return
     * @throws ApiException
     */
    public LsOutputData ls(String userDN, List<String> userFQANS, List<String> surls, RecursionLevel recursion)
            throws ApiException
    {
        return LsExecutor.execute(storm, userDN, userFQANS, surls, recursion);
    }
    
    
    public LsOutputData ls(String userDN, List<String> userFQANS, List<String> surls, Integer count) throws ApiException
    {
        return LsExecutor.execute(storm, userDN, userFQANS, surls, count);
    }
    
    public LsOutputData ls(String userDN, List<String> userFQANS,
            List<String> surls, RecursionLevel recursion, Integer count) throws ApiException
    {
        return LsExecutor.execute(storm, userDN, userFQANS, surls, recursion, count);
    }

    public LsOutputData lsDetailed(String userDN, List<String> userFQANS, List<String> surls) throws ApiException
    {
        return LsExecutor.executeDetailed(storm, userDN, userFQANS, surls);
    }

    public LsOutputData lsDetailed(String userDN, List<String> userFQANS, List<String> surls, RecursionLevel recursion)
            throws ApiException
    {
        return LsExecutor.executeDetailed(storm, userDN, userFQANS, surls, recursion);
    }
        
    public LsOutputData lsDetailed(String userDN, List<String> userFQANS, List<String> surls, Integer count) throws ApiException
    {
        return LsExecutor.executeDetailed(storm, userDN, userFQANS, surls, count);
    }
    
    public LsOutputData lsDetailed(String userDN, List<String> userFQANS,
            List<String> surls, RecursionLevel recursion, Integer count) throws ApiException
    {
        return LsExecutor.executeDetailed(storm, userDN, userFQANS, surls, recursion, count);
    }
    
    public FileTransferOutputData prepareToPut(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return PtPExecutor.execute(storm, userDN, userFQANS, surl);
    }

    public FileTransferOutputData prepareToPut(String userDN, List<String> userFQANS, String surl, List<String> transferProtocols) throws ApiException
    {
        return PtPExecutor.execute(storm, userDN, userFQANS, surl, transferProtocols);
    }

    public FileTransferOutputData prepareToPut(String userDN, List<String> userFQANS, String surl, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.execute(storm, userDN, userFQANS, surl, desiredFileLifetime, desiredPinLifetime);
       
    }
    
    public FileTransferOutputData prepareToPut(String userDN, List<String> userFQANS, String surl,
            List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.execute(storm, userDN, userFQANS, surl, transferProtocols, desiredFileLifetime, desiredPinLifetime);
    }
    
    public FileTransferOutputData prepareToPutOverwrite(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, userDN, userFQANS, surl);
    }

    public FileTransferOutputData prepareToPutOverwrite(String userDN, List<String> userFQANS, String surl, List<String> transferProtocols) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, userDN, userFQANS, surl, transferProtocols);
    }

    public FileTransferOutputData prepareToPutOverwrite(String userDN, List<String> userFQANS, String surl, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, userDN, userFQANS, surl, desiredFileLifetime, desiredPinLifetime);
       
    }
    
    public FileTransferOutputData prepareToPutOverwrite(String userDN, List<String> userFQANS, String surl,
            List<String> transferProtocols, TLifeTimeInSeconds desiredFileLifetime, TLifeTimeInSeconds desiredPinLifetime) throws ApiException
    {
        return PtPExecutor.executeOverwrite(storm, userDN, userFQANS, surl, transferProtocols, desiredFileLifetime, desiredPinLifetime);
    }
    
    public PtGOutputData prepareToGet(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return PtGExecutor.execute(storm, userDN, userFQANS, surl);
    }
    
    public PtGOutputData prepareToGet(String userDN, List<String> userFQANS, String surl, List<String> transferProtocols) throws ApiException
    {
        return PtGExecutor.execute(storm, userDN, userFQANS, surl, transferProtocols);
    }
    
    public SurlArrayRequestOutputData releaseFiles(String userDN, List<String> userFQANS, List<String> surls, TRequestToken requestToken) throws ApiException
    {
        return ReleaseFilesExecutor.execute(storm, userDN, userFQANS, surls, requestToken);
    }

    public OutputData extendFileLifeTime(String userDN, List<String> userFQANS, String surl, long fileLifetime) throws ApiException
    {
        throw new ApiException("Unable to perform extendFileLifeTime call. Not implemented");
    }
    
    public OutputData extendPinLifeTime(String userDN, List<String> userFQANS, TRequestToken requestToken, String surl, long pinLifetime) throws ApiException
    {
        throw new ApiException("Unable to perform extendPinLifeTime call. Not implemented");
    }

    public RequestOutputData abortRequest(String userDN, List<String> userFQANS, TRequestToken requestToken) throws ApiException
    {
        return AbortRequestExecutor.execute(storm, userDN, userFQANS, requestToken);
    }

    public SurlArrayRequestOutputData abortFiles(String userDN, List<String> userFQANS, TRequestToken requestToken, List<String> surls) throws ApiException
    {
        return AbortFilesExecutor.execute(storm, userDN, userFQANS, surls, requestToken);
    }

    public OutputData reserveSpace(String userDN, List<String> userFQANS, String spaceAlias,
            TRetentionPolicyInfo retentionPolicyInfo, TSizeInBytes desiredSizeOfTotalSpace,
            TSizeInBytes desiredSizeOfGuaranteedSpace, TLifeTimeInSeconds desiredLifetimeOfReservedSpace,
            ArrayOfTExtraInfo storageSystemInfo) throws ApiException
    {
        throw new ApiException("Unable to perform reserveSpace call. Not implemented");
    }

    public OutputData reserveSpace(String userDN, List<String> userFQANS,
            TRetentionPolicyInfo retentionPolicyInfo, TSizeInBytes desiredSizeOfTotalSpace,
            TSizeInBytes desiredSizeOfGuaranteedSpace, TLifeTimeInSeconds desiredLifetimeOfReservedSpace,
            ArrayOfTExtraInfo storageSystemInfo) throws ApiException
    {
        throw new ApiException("Unable to perform reserveSpace call. Not implemented");
    }
    
    public OutputData getSpaceMetaData(String userDN, List<String> userFQANS, ArrayOfTSpaceToken arrayOfSpaceTokens) throws ApiException
    {
        throw new ApiException("Unable to perform getSpaceMetaData call. Not implemented");
    }

    public OutputData getSpaceTokens(String userDN, List<String> userFQANS, String spaceTokenDescription) throws ApiException
    {
        throw new ApiException("Unable to perform getSpaceTokens call. Not implemented");
    }

    public OutputData releaseSpace(String userDN, List<String> userFQANS, TSpaceToken spaceToken) throws ApiException
    {
        throw new ApiException("Unable to perform releaseSpace call. Not implemented");
    }

    public OutputData releaseSpaceForced(String userDN, List<String> userFQANS, TSpaceToken spaceToken) throws ApiException
    {
        throw new ApiException("Unable to perform releaseSpaceForced call. Not implemented");
    }
    
    public RequestOutputData mkdir(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return MkdirExecutor.execute(storm, userDN, userFQANS, surl);
    }

    public RequestOutputData rmdir(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return RmdirExecutor.execute(storm, userDN, userFQANS, surl);
    }
    
    public RequestOutputData rmdirRecursively(String userDN, List<String> userFQANS, String surl) throws ApiException
    {
        return RmdirExecutor.executeRecursive(storm, userDN, userFQANS, surl);
    }

    public RequestOutputData rm(String userDN, List<String> userFQANS, List<String> surls) throws ApiException
    {
        return RmExecutor.execute(storm, userDN, userFQANS, surls);
    }

    public RequestOutputData mv(String userDN, List<String> userFQANS, String fromSurl, String toSurl) throws ApiException
    {
        return MvExecutor.execute(storm, userDN, userFQANS, fromSurl, toSurl);
    }
}