import { Observable } from 'rxjs';

import { RegisterMovementRequest } from './request/register-movement.request';
import { RegisterMovementResponse } from './response/register-movement.response';
import { MovementListItem } from '../model/movement-list-item.model';

export abstract class MovementService {

    public abstract registerIncomingMovement(productId: string, request: RegisterMovementRequest): Observable<RegisterMovementResponse> 

    public abstract registerOutgoingMovement(productId: string, request: RegisterMovementRequest): Observable<RegisterMovementResponse> 

    public abstract getAllMovements(): Observable<MovementListItem[]>;

    public abstract getAllMovementsByProduct(productId: string): Observable<MovementListItem[]>;

}