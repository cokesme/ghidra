/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.app.cmd.refs;

import ghidra.framework.cmd.Command;
import ghidra.program.model.listing.Program;
import ghidra.util.exception.InvalidInputException;

/**
 * Command for setting the external program name and path.
 * 
 * 
 */
public class SetExternalNameCmd implements Command<Program> {

	private String externalName;
	private String externalPath;
	private String status;
	private boolean userDefined = true;

	/**
	 * Constructs a new command for setting the external program name and path.
	 * @param externalName the name of the link.
	 * @param externalPath the path of the file to assocate with this link.
	 */
	public SetExternalNameCmd(String externalName, String externalPath) {
		this.externalName = externalName;
		this.externalPath = externalPath;
	}

	@Override
	public boolean applyTo(Program program) {
		try {
			program.getExternalManager().setExternalPath(externalName, externalPath, userDefined);
		}
		catch (InvalidInputException e) {
			status = "Invalid name specified";
			return false;
		}
		return true;
	}

	@Override
	public String getStatusMsg() {
		return status;
	}

	@Override
	public String getName() {
		return "Set External Program Name";
	}

}
